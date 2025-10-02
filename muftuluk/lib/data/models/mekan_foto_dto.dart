class MekanFotoDto {
  final String url;
  final bool isKapakFoto;
  final int sira;

  MekanFotoDto({
    required this.url,
    required this.isKapakFoto,
    required this.sira,
  });

  factory MekanFotoDto.fromJson(Map<String, dynamic> json) {
    return MekanFotoDto(
      url: json['url'],
      isKapakFoto: json['isKapakFoto'],
      sira: json['sira'],
    );
  }
}
