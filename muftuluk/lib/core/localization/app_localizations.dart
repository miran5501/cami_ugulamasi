class AppLocalizations {
  final String langCode;
  AppLocalizations(this.langCode);

  static final Map<String, Map<String, String>> _localizedValues = {
    'tr': {'home': 'Ana Sayfa', 'settings': 'Ayarlar', 'map': 'Harita'},
    'en': {'home': 'Home', 'settings': 'Settings', 'map': 'Map'},
    'el': {'home': 'Αρχική Σελίδα', 'settings': 'Ρυθμίσεις', 'map': 'Χάρτης'},
  };

  String translate(String key) {
    return _localizedValues[langCode]?[key] ?? key;
  }
}
