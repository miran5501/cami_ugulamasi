import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import '../localization/app_localizations.dart';

class LanguageProvider with ChangeNotifier {
  String _langCode;
  late AppLocalizations _localizations;

  LanguageProvider(String initialLang)
      : _langCode = initialLang,
        _localizations = AppLocalizations(initialLang);

  String get langCode => _langCode;
  AppLocalizations get localizations => _localizations;

  Future<void> setLanguage(String code) async {
    _langCode = code;
    _localizations = AppLocalizations(code);

    final prefs = await SharedPreferences.getInstance();
    await prefs.setString("langCode", code);

    notifyListeners();
  }
}
