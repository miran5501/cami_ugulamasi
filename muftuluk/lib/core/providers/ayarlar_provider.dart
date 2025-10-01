import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class SettingsProvider with ChangeNotifier {
  bool _isDarkMode = false;
  bool _ezanNotifications = true;
  String _language = "tr";

  bool get isDarkMode => _isDarkMode;
  bool get ezanNotifications => _ezanNotifications;
  String get language => _language;

  SettingsProvider() {
    _loadSettings(); // açılışta kaydedilen ayarları yükle
  }

  Future<void> _loadSettings() async {
    final prefs = await SharedPreferences.getInstance();
    _isDarkMode = prefs.getBool("darkMode") ?? false;
    _ezanNotifications = prefs.getBool("ezanNotifications") ?? true;
    _language = prefs.getString("language") ?? "tr";
    notifyListeners();
  }

  Future<void> toggleDarkMode(bool value) async {
    _isDarkMode = value;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool("darkMode", value);
    notifyListeners();
  }

  Future<void> toggleEzanNotifications(bool value) async {
    _ezanNotifications = value;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setBool("ezanNotifications", value);
    notifyListeners();
  }

  Future<void> changeLanguage(String lang) async {
    _language = lang;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString("language", lang);
    notifyListeners();
  }
}
