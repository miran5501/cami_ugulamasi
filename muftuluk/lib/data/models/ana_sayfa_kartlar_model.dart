import 'dart:convert';

class AnaSayfaKartModel {
  final String id; // UUID string
  final String? ad;
  final String? adres;
  final String? kapakFotograf;
  final String? bolgeAd;
  final double enlem;
  final double boylam;
  final double mesafe;

  AnaSayfaKartModel({
    required this.id,
    required this.ad,
    required this.adres,
    required this.kapakFotograf,
    required this.bolgeAd,
    required this.enlem,
    required this.boylam,
    required this.mesafe,
  });

  /// JSON'dan model oluşturma
  factory AnaSayfaKartModel.fromJson(Map<String, dynamic> json) {
    return AnaSayfaKartModel(
      id: json['id'],
      ad: json['ad'],
      adres: json['adres'],
      kapakFotograf: json['kapakFotograf'],
      bolgeAd: json['bolgeAd'],
      enlem: (json['enlem'] as num).toDouble(),
      boylam: (json['boylam'] as num).toDouble(),
      mesafe: (json['mesafe'] as num).toDouble(),
    );
  }

  /// Modele göre JSON üretme
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'ad': ad,
      'adres': adres,
      'kapakFotograf': kapakFotograf,
      'bolgeAd': bolgeAd,
      'enlem': enlem,
      'boylam': boylam,
      'mesafe': mesafe,
    };
  }

  /// JSON string'ten parse etme kolaylığı
  static AnaSayfaKartModel fromJsonString(String str) =>
      AnaSayfaKartModel.fromJson(json.decode(str));

  /// Modele göre JSON string oluşturma
  String toJsonString() => json.encode(toJson());
}
