import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:muftuluk/core/constants/api_constants.dart';
import 'package:muftuluk/data/models/api_response.dart';
import 'package:muftuluk/data/models/mekan_tarihce_model.dart';

class MekanTarihceService {
  Future<List<MekanTarihceModel>> fetchTarihce(
    String id,
    String dilKodu,
  ) async {
    final response = await http.get(
      Uri.parse("${ApiConstants.baseUrl}/mekan/$id/tarihce?dilKodu=$dilKodu"),
      headers: {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "true",
      },
    );

    if (response.statusCode == 200) {
      final Map<String, dynamic> jsonData = json.decode(response.body);

      final apiResponse = ApiResponse.fromJson(
        jsonData,
        (data) =>
            (data as List).map((e) => MekanTarihceModel.fromJson(e)).toList(),
      );

      if (apiResponse.success) {
        return apiResponse.data ?? [];
      } else {
        throw Exception("Hata: ${apiResponse.message}");
      }
    } else {
      throw Exception("Veri alınamadı: ${response.statusCode}");
    }
  }
}
