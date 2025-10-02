import 'mekan_foto_dto.dart';

class MekanDetayModel {
  final String id;
  final String ad;
  final String? adres;
  final String? bolgeAd;
  final double enlem;
  final double boylam;
  final double? mesafe;
  final List<MekanFotoDto> mekanFotoList;

  MekanDetayModel({
    required this.id,
    required this.ad,
    this.adres,
    this.bolgeAd,
    required this.enlem,
    required this.boylam,
    this.mesafe,
    required this.mekanFotoList,
  });

  factory MekanDetayModel.fromJson(Map<String, dynamic> json) {
    return MekanDetayModel(
      id: json['id'],
      ad: json['ad'],
      adres: json['adres'],
      bolgeAd: json['bolgeAd'],
      enlem: (json['enlem'] as num).toDouble(),
      boylam: (json['boylam'] as num).toDouble(),
      mesafe: json['mesafe'] != null
          ? (json['mesafe'] as num).toDouble()
          : null,
      mekanFotoList: (json['mekanFotoList'] as List<dynamic>)
          .map((e) => MekanFotoDto.fromJson(e))
          .toList(),
    );
  }
}
