import 'package:flutter/material.dart';
import 'package:muftuluk/presentation/screens/ayarlar/ayarlar.dart';
import 'package:muftuluk/presentation/screens/harita/harita.dart'; // ✅ Harita ekranını import et
import 'package:provider/provider.dart';
import 'package:muftuluk/core/providers/language_provider.dart';

class AnaSayfaHeader extends StatelessWidget {
  const AnaSayfaHeader({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final lang = Provider.of<LanguageProvider>(context).localizations;

    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        // 🔹 Konum & Ayarlar
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Row(
                children: [
                  Icon(
                    Icons.location_on,
                    color: theme.iconTheme.color?.withOpacity(0.7),
                    size: 18,
                  ),
                  const SizedBox(width: 4),
                  Text(
                    "${lang.translate("location")}: İzmit",
                    style: TextStyle(
                      color: theme.textTheme.bodySmall?.color?.withOpacity(0.7),
                      fontSize: 14,
                    ),
                  ),
                ],
              ),
              Row(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (_) => const SettingsScreen(),
                        ),
                      );
                    },
                    icon: Icon(
                      Icons.settings,
                      color: theme.iconTheme.color?.withOpacity(0.7),
                    ),
                  ),
                  const SizedBox(width: 8),
                  GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (_) =>
                              const HaritaScreen(), // ✅ Harita sayfasına git
                        ),
                      );
                    },
                    child: Text(
                      lang.translate("map_view"),
                      style: TextStyle(
                        color: theme.colorScheme.secondary,
                        fontSize: 14,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),

        const SizedBox(height: 12),

        // 🔹 Başlık
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Text(
            lang.translate("nearby_mosques"),
            style: theme.textTheme.bodyLarge?.copyWith(
              fontSize: 20,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),

        const SizedBox(height: 16),

        // 🔹 Filtrele & Sırala
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              OutlinedButton.icon(
                style: OutlinedButton.styleFrom(
                  side: BorderSide(color: theme.colorScheme.secondary),
                  foregroundColor: theme.colorScheme.secondary,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(12),
                  ),
                ),
                onPressed: () {
                  // Filtrele işlemi
                },
                icon: const Icon(Icons.filter_list, size: 18),
                label: Text(lang.translate("filter")),
              ),
              Text(
                "${lang.translate("sort")}: ${lang.translate("nearest")}",
                style: theme.textTheme.bodySmall?.copyWith(
                  color: theme.textTheme.bodySmall?.color?.withOpacity(0.7),
                  fontSize: 14,
                ),
              ),
            ],
          ),
        ),

        const SizedBox(height: 12),
      ],
    );
  }
}
