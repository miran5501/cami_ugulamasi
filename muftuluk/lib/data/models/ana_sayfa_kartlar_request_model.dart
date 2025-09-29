class AnaSayfaKartlarRequest {
  final String? dilKodu;
  final double? enlem;
  final double? boylam;

  AnaSayfaKartlarRequest({required this.dilKodu, this.enlem, this.boylam});

  Map<String, dynamic> toJson() {
    return {"dilKodu": dilKodu, "enlem": enlem, "boylam": boylam};
  }
}
