import 'package:flutter/material.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_model.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_request_model.dart';
import 'package:muftuluk/data/services/ana_sayfa_kartlar_service.dart';
import 'package:flutter_map_animations/flutter_map_animations.dart'; // ✅ doğru paket

class HaritaScreen extends StatefulWidget {
  const HaritaScreen({super.key});

  @override
  State<HaritaScreen> createState() => _HaritaScreenState();
}

class _HaritaScreenState extends State<HaritaScreen>
    with TickerProviderStateMixin {
  final AnaSayfaKartlarService _service = AnaSayfaKartlarService();
  late Future<List<AnaSayfaKartModel>> futureKartlar;

  static const LatLng _defaultLocation = LatLng(41.0053, 28.9768); // İstanbul

  AnaSayfaKartModel? _selectedCami;
  List<AnaSayfaKartModel> _camiler = [];

  late final AnimatedMapController _mapController; // ✅ animasyonlu controller

  @override
  void initState() {
    super.initState();
    _mapController = AnimatedMapController(vsync: this); // ✅ init
    futureKartlar = _service.fetchKartlar(
      AnaSayfaKartlarRequest(dilKodu: "tr", enlem: 41.0053, boylam: 28.9768),
    );
  }

  void _selectCami(AnaSayfaKartModel cami) {
    setState(() {
      _selectedCami = cami;
      _camiler.remove(cami);
      _camiler.insert(0, cami);
    });

    // ✅ Yumuşak animasyonla merkeze al
    _mapController.animateTo(
      dest: LatLng(cami.enlem, cami.boylam),
      zoom: 15,
      duration: const Duration(seconds: 1),
      curve: Curves.easeInOut,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Harita Görünümü")),
      body: FutureBuilder<List<AnaSayfaKartModel>>(
        future: futureKartlar,
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          }
          if (snapshot.hasError) {
            return Center(child: Text("Hata: ${snapshot.error}"));
          }
          if (!snapshot.hasData || snapshot.data!.isEmpty) {
            return const Center(child: Text("Hiç cami bulunamadı"));
          }

          if (_camiler.isEmpty) {
            _camiler = List.from(snapshot.data!);
          }

          final markers = _camiler.map((cami) {
            return Marker(
              point: LatLng(cami.enlem, cami.boylam),
              width: 40,
              height: 40,
              child: GestureDetector(
                onTap: () => _selectCami(cami),
                child: Icon(
                  Icons.location_on,
                  color: _selectedCami == cami ? Colors.blueAccent : Colors.red,
                  size: 32,
                ),
              ),
            );
          }).toList();

          return Column(
            children: [
              // Harita
              Expanded(
                flex: 2,
                child: FlutterMap(
                  mapController:
                      _mapController.mapController, // ✅ burası önemli
                  options: MapOptions(
                    initialCenter: _camiler.isNotEmpty
                        ? LatLng(_camiler.first.enlem, _camiler.first.boylam)
                        : _defaultLocation,
                    initialZoom: 13,
                  ),
                  children: [
                    TileLayer(
                      urlTemplate:
                          "https://tile.openstreetmap.org/{z}/{x}/{y}.png",
                      userAgentPackageName: 'com.example.muftuluk',
                    ),
                    MarkerLayer(markers: markers),
                  ],
                ),
              ),

              // Liste
              Expanded(
                flex: 2,
                child: ListView.builder(
                  padding: const EdgeInsets.all(12),
                  itemCount: _camiler.length,
                  itemBuilder: (context, index) {
                    final cami = _camiler[index];
                    final isSelected = _selectedCami == cami;

                    return GestureDetector(
                      onTap: () => _selectCami(cami),
                      child: AnimatedContainer(
                        duration: const Duration(milliseconds: 300),
                        margin: const EdgeInsets.only(bottom: 12),
                        padding: const EdgeInsets.all(12),
                        decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(16),
                          border: Border.all(
                            color: isSelected
                                ? Colors.blueAccent
                                : Colors.grey.shade300,
                            width: 2,
                          ),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.black.withOpacity(0.1),
                              blurRadius: 5,
                            ),
                          ],
                        ),
                        child: Row(
                          children: [
                            ClipRRect(
                              borderRadius: BorderRadius.circular(12),
                              child:
                                  cami.kapakFotograf != null &&
                                      cami.kapakFotograf!.isNotEmpty
                                  ? Image.network(
                                      cami.kapakFotograf!,
                                      width: 60,
                                      height: 60,
                                      fit: BoxFit.cover,
                                    )
                                  : Container(
                                      width: 60,
                                      height: 60,
                                      color: Colors.grey.shade200,
                                      child: const Icon(
                                        Icons.mosque,
                                        color: Colors.grey,
                                      ),
                                    ),
                            ),
                            const SizedBox(width: 12),
                            Expanded(
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    cami.ad ?? "İsimsiz",
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                      color: isSelected
                                          ? Colors.blueAccent
                                          : Colors.black,
                                    ),
                                  ),
                                  const SizedBox(height: 4),
                                  Text(
                                    cami.adres ?? "Adres yok",
                                    style: const TextStyle(
                                      fontSize: 13,
                                      color: Colors.black54,
                                    ),
                                    maxLines: 1,
                                    overflow: TextOverflow.ellipsis,
                                  ),
                                  const SizedBox(height: 4),
                                  Text(
                                    "${cami.mesafe.toStringAsFixed(0)} m",
                                    style: TextStyle(
                                      fontSize: 12,
                                      color: isSelected
                                          ? Colors.blueAccent
                                          : Colors.grey,
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            Icon(
                              Icons.info,
                              color: isSelected
                                  ? Colors.blueAccent
                                  : Colors.grey,
                            ),
                          ],
                        ),
                      ),
                    );
                  },
                ),
              ),
            ],
          );
        },
      ),
    );
  }
}
