import 'dart:convert';

class MekanTarihceModel {
  final String paragraf;
  final String? resimUrl;
  final bool isBaslik;
  final int sira;

  MekanTarihceModel({
    required this.paragraf,
    this.resimUrl,
    required this.isBaslik,
    required this.sira,
  });

  factory MekanTarihceModel.fromJson(Map<String, dynamic> json) {
    return MekanTarihceModel(
      paragraf: json['paragraf'] ?? "",
      resimUrl: json['resimUrl'],
      isBaslik: json['isBaslik'] ?? false,
      sira: json['sira'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      "paragraf": paragraf,
      "resimUrl": resimUrl,
      "isBaslik": isBaslik,
      "sira": sira,
    };
  }

  static MekanTarihceModel fromJsonString(String str) =>
      MekanTarihceModel.fromJson(json.decode(str));

  String toJsonString() => json.encode(toJson());
}
