import 'package:flutter/material.dart';
import 'package:muftuluk/presentation/screens/detay/cami_tarihce_header.dart';
import 'package:provider/provider.dart';
import 'package:muftuluk/core/providers/language_provider.dart';
import 'package:muftuluk/data/models/mekan_tarihce_model.dart';
import 'package:muftuluk/data/services/mekan_tarihce_service.dart';

class TarihceScreen extends StatefulWidget {
  final String mekanId;

  const TarihceScreen({super.key, required this.mekanId});

  @override
  State<TarihceScreen> createState() => _TarihceScreenState();
}

class _TarihceScreenState extends State<TarihceScreen> {
  final MekanTarihceService _service = MekanTarihceService();
  late Future<List<MekanTarihceModel>> futureTarihce;

  @override
  void initState() {
    super.initState();

    // ✅ Provider'dan dil kodunu al
    final dilKodu = Provider.of<LanguageProvider>(
      context,
      listen: false,
    ).langCode;

    futureTarihce = _service.fetchTarihce(widget.mekanId, dilKodu);
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final lang = Provider.of<LanguageProvider>(context).localizations;

    return Scaffold(
      appBar: AppBar(
        title: Text(lang.translate("history")), // çok dilli başlık
      ),
      body: Column(
        children: [
          // ✅ Header kısmı (mekan detaylarını gösterir)
          MekanHeader(mekanId: widget.mekanId),

          // ✅ Tarihçe listesi
          Expanded(
            child: FutureBuilder<List<MekanTarihceModel>>(
              future: futureTarihce,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return const Center(child: CircularProgressIndicator());
                }
                if (snapshot.hasError) {
                  return Center(
                    child: Text(
                      "${lang.translate("error")}: ${snapshot.error}",
                      style: const TextStyle(color: Colors.red),
                    ),
                  );
                }
                if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(child: Text(lang.translate("no_history")));
                }

                final tarihceList = snapshot.data!;

                return ListView.builder(
                  padding: const EdgeInsets.all(16),
                  itemCount: tarihceList.length,
                  itemBuilder: (context, index) {
                    final blok = tarihceList[index];

                    if (blok.isBaslik) {
                      // Başlık kısmı
                      return Padding(
                        padding: const EdgeInsets.symmetric(vertical: 12),
                        child: Text(
                          blok.paragraf,
                          style: theme.textTheme.titleLarge?.copyWith(
                            fontWeight: FontWeight.bold,
                            color: theme.colorScheme.secondary,
                          ),
                        ),
                      );
                    } else {
                      // Paragraf + resim
                      final bool resimSolda = index % 2 == 0;

                      return Padding(
                        padding: const EdgeInsets.symmetric(vertical: 12),
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            if (blok.resimUrl != null &&
                                blok.resimUrl!.isNotEmpty &&
                                resimSolda) ...[
                              _buildImage(blok.resimUrl!),
                              const SizedBox(width: 12),
                            ],
                            Expanded(
                              child: Text(
                                blok.paragraf,
                                style: theme.textTheme.bodyMedium,
                                textAlign: TextAlign.justify,
                              ),
                            ),
                            if (blok.resimUrl != null &&
                                blok.resimUrl!.isNotEmpty &&
                                !resimSolda) ...[
                              const SizedBox(width: 12),
                              _buildImage(blok.resimUrl!),
                            ],
                          ],
                        ),
                      );
                    }
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildImage(String url) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(8),
      child: Image.network(url, width: 80, height: 80, fit: BoxFit.cover),
    );
  }
}
