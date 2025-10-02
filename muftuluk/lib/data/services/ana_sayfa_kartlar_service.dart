import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:muftuluk/core/constants/api_constants.dart';
import 'package:muftuluk/data/models/api_response.dart';
import '../models/ana_sayfa_kartlar_model.dart';
import '../models/ana_sayfa_kartlar_request_model.dart';

class AnaSayfaKartlarService {
  /// Mekan kartlarını getir (POST request ile)
  Future<List<AnaSayfaKartModel>> fetchKartlar(
    AnaSayfaKartlarRequest request,
  ) async {
    try {
      final response = await http.post(
        Uri.parse(ApiConstants.kartlar),
        headers: {
          "Content-Type": "application/json",
          "ngrok-skip-browser-warning": "true",
        },
        body: jsonEncode(request.toJson()),
      );

      if (response.statusCode == 200) {
        final Map<String, dynamic> jsonData = json.decode(response.body);

        // ApiResponse'u parse et
        final apiResponse = ApiResponse.fromJson(
          jsonData,
          (data) =>
              (data as List).map((e) => AnaSayfaKartModel.fromJson(e)).toList(),
        );

        if (apiResponse.success) {
          return apiResponse.data ?? [];
        } else {
          throw Exception("Hata: ${apiResponse.message}");
        }
      } else {
        throw Exception("Veri alınamadı: ${response.statusCode}");
      }
    } catch (e) {
      throw Exception("Hata: $e");
    }
  }
}
