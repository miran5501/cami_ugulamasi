class AppLocalizations {
  final String langCode;
  AppLocalizations(this.langCode);

  static Map<String, Map<String, String>> _localizedValues = {
    'tr': {
      'settings': 'Ayarlar',
      'dark_theme': 'Karanlık Tema',
      'choose_language': 'Dil Seç',
      'nearby_mosques': 'Yakındaki Camiler',
      'no_mosque_found': 'Hiç cami bulunamadı',
      'no_data': 'Hiç veri bulunamadı',
      'error': 'Hata',
      'unnamed': 'İsimsiz',
      'no_address': 'Adres yok',
      'no_photo': 'Fotoğraf Yok',
      'location': 'Konumunuz',
      'map_view': 'Harita Görünümü',
      'filter': 'Filtrele',
      'sort': 'Sırala',
      'nearest': 'En Yakın',
    },
    'en': {
      'settings': 'Settings',
      'dark_theme': 'Dark Theme',
      'choose_language': 'Choose Language',
      'nearby_mosques': 'Nearby Mosques',
      'no_mosque_found': 'No mosque found',
      'no_data': 'No data found',
      'error': 'Error',
      'unnamed': 'Unnamed',
      'no_address': 'No address',
      'no_photo': 'No Photo',
      'location': 'Location',
      'map_view': 'Map View',
      'filter': 'Filter',
      'sort': 'Sort',
      'nearest': 'Nearest',
    },
    'el': {
      'settings': 'Ρυθμίσεις',
      'dark_theme': 'Σκούρο θέμα',
      'choose_language': 'Επιλογή γλώσσας',
      'nearby_mosques': 'Κοντινά Τζαμιά',
      'no_mosque_found': 'Δεν βρέθηκαν τζαμιά',
      'no_data': 'Δεν βρέθηκαν δεδομένα',
      'error': 'Σφάλμα',
      'unnamed': 'Χωρίς όνομα',
      'no_address': 'Χωρίς διεύθυνση',
      'no_photo': 'Χωρίς φωτογραφία',
      'location': 'Τοποθεσία',
      'map_view': 'Προβολή Χάρτη',
      'filter': 'Φίλτρο',
      'sort': 'Ταξινόμηση',
      'nearest': 'Πλησιέστερο',
    },
  };

  String translate(String key) {
    return _localizedValues[langCode]?[key] ?? key;
  }
}
