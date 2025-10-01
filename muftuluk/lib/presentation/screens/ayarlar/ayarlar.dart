import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '/core/providers/theme_provider.dart';
import '/core/providers/language_provider.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final themeProvider = Provider.of<ThemeProvider>(context);
    final langProvider = Provider.of<LanguageProvider>(context);
    final lang = langProvider.localizations;

    return Scaffold(
      appBar: AppBar(title: Text(lang.translate("settings"))),
      body: ListView(
        children: [
          // 🔹 Tema Seçimi
          SwitchListTile(
            title: Text(lang.translate("dark_theme")),
            value: themeProvider.themeMode == ThemeMode.dark,
            onChanged: (val) {
              themeProvider.toggleTheme(val);
            },
          ),

          // 🔹 Dil Seçimi
          ListTile(
            title: Text(lang.translate("choose_language")),
            trailing: DropdownButton<String>(
              value: langProvider.langCode,
              items: const [
                DropdownMenuItem(value: "tr", child: Text("Türkçe")),
                DropdownMenuItem(value: "en", child: Text("English")),
                DropdownMenuItem(value: "el", child: Text("Ελληνικά")),
              ],
              onChanged: (val) {
                if (val != null) {
                  langProvider.setLanguage(val);
                }
              },
            ),
          ),
        ],
      ),
    );
  }
}
