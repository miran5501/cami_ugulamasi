import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:muftuluk/core/constants/api_constants.dart';
import 'package:muftuluk/data/models/api_response.dart';
import 'package:muftuluk/data/models/mekan_detay_model.dart';
import 'package:muftuluk/data/models/ana_sayfa_kartlar_request_model.dart';

class MekanDetayService {
  Future<MekanDetayModel> fetchMekanDetay(String id, AnaSayfaKartlarRequest request) async {
    final response = await http.post(
      Uri.parse("${ApiConstants.baseUrl}/mekan/$id/detay"),
      headers: {
        "Content-Type": "application/json",
        "ngrok-skip-browser-warning": "true",
      },
      body: jsonEncode(request.toJson()),
    );

    if (response.statusCode == 200) {
      final jsonData = json.decode(response.body);
      final apiResponse = ApiResponse.fromJson(
        jsonData,
        (data) => MekanDetayModel.fromJson(data),
      );

      if (apiResponse.success) {
        return apiResponse.data!;
      } else {
        throw Exception(apiResponse.message);
      }
    } else {
      throw Exception("Veri alınamadı: ${response.statusCode}");
    }
  }
}
