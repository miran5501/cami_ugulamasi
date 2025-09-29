import 'package:flutter/material.dart';
import 'app_localizations.dart';

class LanguageProvider with ChangeNotifier {
  String _langCode = "tr";
  AppLocalizations _localizations = AppLocalizations("tr");

  String get langCode => _langCode;
  AppLocalizations get localizations => _localizations;

  void setLanguage(String code) {
    _langCode = code;
    _localizations = AppLocalizations(code);
    notifyListeners();
  }
}
