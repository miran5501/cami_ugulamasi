import 'package:flutter/material.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_model.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_request_model.dart';
import 'package:muftuluk/data/services/ana_sayfa_kartlar_service.dart';
import 'package:provider/provider.dart';

// 👇 header.dart import
import './header.dart';
import 'package:muftuluk/core/providers/language_provider.dart';
import 'package:muftuluk/core/localization/app_localizations.dart';

class AnaSayfaScreen extends StatefulWidget {
  const AnaSayfaScreen({super.key});

  @override
  State<AnaSayfaScreen> createState() => _AnaSayfaScreenState();
}

class _AnaSayfaScreenState extends State<AnaSayfaScreen> {
  final AnaSayfaKartlarService _service = AnaSayfaKartlarService();
  late Future<List<AnaSayfaKartModel>> futureKartlar;
  int? selectedIndex;

  @override
  void initState() {
    super.initState();
    futureKartlar = _service.fetchKartlar(
      AnaSayfaKartlarRequest(dilKodu: "tr", enlem: 41.0053, boylam: 28.9768),
    );
  }

  @override
  Widget build(BuildContext context) {
    final lang = Provider.of<LanguageProvider>(context).localizations;
    final theme = Theme.of(context);

    return Scaffold(
      backgroundColor: theme.scaffoldBackgroundColor,
      appBar: AppBar(
        title: Text(lang.translate("nearby_mosques")),
        backgroundColor:
            theme.appBarTheme.backgroundColor ?? theme.scaffoldBackgroundColor,
      ),
      body: Column(
        children: [
          // 👇 Header bileşeni
          const AnaSayfaHeader(),

          // Liste builder
          Expanded(
            child: FutureBuilder<List<AnaSayfaKartModel>>(
              future: futureKartlar,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                }
                if (snapshot.hasError) {
                  return Center(
                    child: Text(
                      "${lang.translate("error")}: ${snapshot.error}",
                      style: theme.textTheme.bodyMedium,
                    ),
                  );
                }
                if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(
                    child: Text(
                      lang.translate("no_data"),
                      style: theme.textTheme.bodyMedium,
                    ),
                  );
                }

                final kartlar = snapshot.data!;
                return ListView.builder(
                  itemCount: kartlar.length,
                  itemBuilder: (context, index) {
                    final kart = kartlar[index];
                    final bool isSelected = selectedIndex == index;

                    return GestureDetector(
                      onTap: () {
                        setState(() {
                          selectedIndex = (selectedIndex == index)
                              ? null
                              : index;
                        });
                      },
                      child: AnimatedContainer(
                        duration: const Duration(milliseconds: 400),
                        curve: Curves.easeInOut,
                        margin: const EdgeInsets.symmetric(
                          horizontal: 12,
                          vertical: 8,
                        ),
                        height: isSelected ? 220 : 100,
                        decoration: BoxDecoration(
                          color: theme.cardColor.withOpacity(0.7),
                          borderRadius: BorderRadius.circular(16),
                          border: Border.all(
                            color: theme.colorScheme.secondary,
                            width: 1,
                          ),
                        ),
                        child: isSelected
                            ? Stack(
                                children: [
                                  // 📌 Fotoğraf sadece açıkken
                                  ClipRRect(
                                    borderRadius: BorderRadius.circular(16),
                                    child:
                                        kart.kapakFotograf != null &&
                                            kart.kapakFotograf!.isNotEmpty
                                        ? Image.network(
                                            kart.kapakFotograf!,
                                            height: double.infinity,
                                            width: double.infinity,
                                            fit: BoxFit.contain,
                                          )
                                        : Container(
                                            color: theme.disabledColor,
                                            alignment: Alignment.center,
                                            child: Text(
                                              lang.translate("no_photo"),
                                              style: theme.textTheme.bodyMedium!
                                                  .copyWith(
                                                    color: theme.disabledColor,
                                                  ),
                                            ),
                                          ),
                                  ),
                                  // 📌 Yazılar (fotoğraf üstünde)
                                  Align(
                                    alignment: Alignment.bottomLeft,
                                    child: Container(
                                      width: double.infinity,
                                      padding: const EdgeInsets.all(12),
                                      decoration: BoxDecoration(
                                        color:
                                            theme.brightness == Brightness.dark
                                            ? Colors.black.withOpacity(
                                                0.6,
                                              ) // dark mod
                                            : Colors.white.withOpacity(
                                                0.6,
                                              ), // light mod
                                        borderRadius: const BorderRadius.only(
                                          bottomLeft: Radius.circular(16),
                                          bottomRight: Radius.circular(16),
                                        ),
                                      ),
                                      child: _buildCardTexts(kart, lang, theme),
                                    ),
                                  ),
                                ],
                              )
                            : Padding(
                                padding: const EdgeInsets.all(12),
                                child: _buildCardTexts(kart, lang, theme),
                              ),
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildCardTexts(
    AnaSayfaKartModel kart,
    AppLocalizations lang,
    ThemeData theme,
  ) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisSize: MainAxisSize.min,
            children: [
              Text(
                kart.ad ?? lang.translate("unnamed"),
                style: theme.textTheme.bodyMedium!.copyWith(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                ),
              ),
              if (kart.adres != null)
                Text(
                  kart.adres!,
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.textTheme.bodySmall?.color?.withOpacity(0.7),
                    fontSize: 14,
                  ),
                  maxLines: 1,
                  overflow: TextOverflow.ellipsis,
                )
              else
                Text(
                  lang.translate("no_address"),
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.textTheme.bodySmall?.color?.withOpacity(0.7),
                    fontSize: 14,
                  ),
                ),
              Text(
                "${kart.mesafe.toStringAsFixed(0)} m",
                style: theme.textTheme.bodySmall?.copyWith(
                  color: theme.colorScheme.secondary,
                  fontSize: 13,
                ),
              ),
            ],
          ),
        ),
        Icon(Icons.arrow_forward_ios, color: theme.colorScheme.secondary),
      ],
    );
  }
}
