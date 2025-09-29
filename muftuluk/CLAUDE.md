# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Flutter application called "muftuluk" (Turkish for "mufti office"), suggesting a religious/Islamic application. The project follows Clean Architecture principles with clear separation of concerns.

**Tech Stack**: Flutter 3.35.4 with Dart 3.9.2, supporting all major platforms (Android, iOS, Web, macOS, Windows, Linux).

## Development Commands

**Essential Commands**:
```bash
flutter pub get       # Install dependencies
flutter run           # Run the app (defaults to available device)
flutter test          # Run all tests
flutter analyze       # Static code analysis
flutter build         # Build for production
```

**Platform-Specific Development**:
```bash
flutter run -d chrome    # Run on web browser
flutter run -d ios       # Run on iOS simulator
flutter run -d android   # Run on Android emulator
flutter run -d macos     # Run on macOS
```

**Testing**:
```bash
flutter test                                    # Run all tests
flutter test test/widget_test.dart             # Run specific test file
flutter test --coverage                        # Run tests with coverage
```

## Architecture

The codebase follows **Clean Architecture** with these layers:

- **`lib/core/`** - Shared utilities, constants, themes, and localization
- **`lib/data/`** - Models, services, and repositories for data access
- **`lib/domain/`** - Business logic and use cases
- **`lib/presentation/`** - UI screens, widgets, and routing
- **`lib/providers/`** - State management (Provider pattern)

**Key Files**:
- `lib/main.dart` - Application entry point
- `lib/data/models/ana_sayfa_kartlar_model.dart` - Home page cards model
- `lib/data/services/ana_sayfa_kartlar_service.dart` - Home page cards service
- `lib/presentation/screens/home_screen.dart` - Home screen implementation

## Code Quality

**Linting**: Uses `flutter_lints ^5.0.0` with Flutter recommended rules. Run `flutter analyze` before committing.

**Dependencies**: Keep `pubspec.yaml` clean. Use `flutter pub deps` to check dependency tree.

## Project Status

The project is in early development stages with architecture scaffolding in place. Most implementation files are currently empty placeholders. The main app still contains the default Flutter counter example and needs to be replaced with actual application logic.