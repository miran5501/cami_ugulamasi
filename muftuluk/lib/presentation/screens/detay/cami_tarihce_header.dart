import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';
import 'package:url_launcher/url_launcher.dart'; // ✅ eklendi
import 'package:muftuluk/core/providers/language_provider.dart';
import 'package:muftuluk/data/models/mekan_detay_model.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_request_model.dart';
import 'package:muftuluk/data/services/mekan_detay_service.dart';

class MekanHeader extends StatefulWidget {
  final String mekanId;

  const MekanHeader({super.key, required this.mekanId});

  @override
  State<MekanHeader> createState() => _MekanHeaderState();
}

class _MekanHeaderState extends State<MekanHeader> {
  final MekanDetayService _service = MekanDetayService();
  late Future<MekanDetayModel> futureDetay;
  final PageController _pageController = PageController();

  @override
  void initState() {
    super.initState();

    final dilKodu = Provider.of<LanguageProvider>(
      context,
      listen: false,
    ).langCode;

    final request = AnaSayfaKartlarRequest(
      dilKodu: dilKodu,
      enlem: 41.0053,
      boylam: 28.9768,
    );

    futureDetay = _service.fetchMekanDetay(widget.mekanId, request);
  }

  // ✅ Google Maps Navigation
  Future<void> _launchNavigation(double lat, double lng) async {
    final Uri googleUrl = Uri.parse(
      "https://www.google.com/maps/dir/?api=1&destination=$lat,$lng",
    );

    try {
      if (!await launchUrl(googleUrl, mode: LaunchMode.externalApplication)) {
        // fallback → tarayıcıda aç
        await launchUrl(googleUrl, mode: LaunchMode.platformDefault);
      }
    } catch (e) {
      debugPrint("Navigasyon hatası: $e");
    }
  }

  // ✅ Konumu paylaşma
  Future<void> _shareLocation(double lat, double lng, String ad) async {
    final Uri shareUrl = Uri.parse(
      "https://www.google.com/maps/search/?api=1&query=$lat,$lng",
    );
    if (await canLaunchUrl(shareUrl)) {
      await launchUrl(shareUrl, mode: LaunchMode.externalApplication);
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return FutureBuilder<MekanDetayModel>(
      future: futureDetay,
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const SizedBox(
            height: 250,
            child: Center(child: CircularProgressIndicator()),
          );
        }

        if (snapshot.hasError) {
          return SizedBox(
            height: 250,
            child: Center(
              child: Text(
                "Hata: ${snapshot.error}",
                style: const TextStyle(color: Colors.red),
              ),
            ),
          );
        }

        if (!snapshot.hasData) {
          return const SizedBox(
            height: 250,
            child: Center(child: Text("Veri bulunamadı")),
          );
        }

        final detay = snapshot.data!;

        return Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // 📌 Fotoğraf Slider
            SizedBox(
              height: 200,
              child: PageView.builder(
                controller: _pageController,
                itemCount: detay.mekanFotoList.length,
                itemBuilder: (context, index) {
                  final foto = detay.mekanFotoList[index];
                  return ClipRRect(
                    borderRadius: BorderRadius.circular(12),
                    child: Image.network(
                      foto.url,
                      fit: BoxFit.cover,
                      width: double.infinity,
                    ),
                  );
                },
              ),
            ),

            // 📌 SmoothPageIndicator
            if (detay.mekanFotoList.isNotEmpty)
              Padding(
                padding: const EdgeInsets.only(top: 8),
                child: Center(
                  child: SmoothPageIndicator(
                    controller: _pageController,
                    count: detay.mekanFotoList.length,
                    effect: WormEffect(
                      dotHeight: 8,
                      dotWidth: 8,
                      activeDotColor: theme.colorScheme.secondary,
                      dotColor: Colors.grey.shade400,
                    ),
                  ),
                ),
              ),

            const SizedBox(height: 12),

            // 📌 Başlık + Adres
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    detay.ad,
                    style: theme.textTheme.titleLarge?.copyWith(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(
                    detay.adres ?? "Adres yok",
                    style: theme.textTheme.bodySmall,
                  ),
                  if (detay.mesafe != null)
                    Text(
                      "${detay.mesafe!.toStringAsFixed(0)} m",
                      style: TextStyle(color: theme.colorScheme.secondary),
                    ),
                  const SizedBox(height: 12),

                  // 🚀 Navigasyon ve Paylaş Butonları
                  Row(
                    children: [
                      Expanded(
                        child: ElevatedButton.icon(
                          onPressed: () {
                            _launchNavigation(detay.enlem, detay.boylam);
                          },
                          icon: const Icon(
                            Icons.navigation,
                            color: Colors.white,
                          ),
                          label: const Text("Navigasyonu Başlat"),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: theme.colorScheme.secondary,
                            foregroundColor: Colors.white,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12),
                            ),
                          ),
                        ),
                      ),
                      const SizedBox(width: 12),
                      Expanded(
                        child: ElevatedButton.icon(
                          onPressed: () {
                            _shareLocation(detay.enlem, detay.boylam, detay.ad);
                          },
                          icon: const Icon(Icons.share, color: Colors.white),
                          label: const Text("Paylaş"),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: Colors.grey.shade600,
                            foregroundColor: Colors.white,
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(12),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ],
        );
      },
    );
  }
}
