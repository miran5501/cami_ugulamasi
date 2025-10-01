import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:muftuluk/core/constants/api_constants.dart';
import '../models/ana_sayfa_kartlar_model.dart';
import '../models/ana_sayfa_kartlar_request_model.dart';

class AnaSayfaKartlarService {
  /// Mekan kartlarını getir (POST request ile)
  Future<List<AnaSayfaKartModel>> fetchKartlar(
    AnaSayfaKartlarRequest request,
  ) async {
    try {
      final response = await http.post(
        Uri.parse(ApiConstants.kartlar), // ngrok linkin burda
        headers: {
          "Content-Type": "application/json",
          "ngrok-skip-browser-warning": "true", // 👈 eklenen kısım
        },
        body: jsonEncode(request.toJson()),
      );

      if (response.statusCode == 200) {
        final List<dynamic> jsonData = json.decode(response.body);
        return jsonData.map((e) => AnaSayfaKartModel.fromJson(e)).toList();
      } else {
        throw Exception("Veri alınamadı: ${response.statusCode}");
      }
    } catch (e) {
      throw Exception("Hata: $e");
    }
  }
}
