# HK Vision Muzei Plugin - Copilot Agent Configuration

## Project Overview

This is an Android application that serves as a Muzei Live Wallpaper plugin, providing wallpapers from the H.K. Vision website (https://vision.hossainkhan.com/).

## Architecture

### Core Components
- **HkVisionArtProvider**: Muzei provider that triggers image loading on schedule
- **HkVisionWorker**: Background worker using AndroidX WorkManager to fetch images
- **HkVisionService**: HTTP client using Retrofit + OkHttp with Moshi JSON parsing
- **HkVisionApi**: API interface for fetching vision photos from the backend

### Technology Stack
- **Language**: Kotlin
- **Build System**: Gradle with Android Gradle Plugin 8.10.1
- **Target SDK**: 35 (Android 14+)
- **Min SDK**: 21 (Android 5.0+)
- **Dependencies**:
  - Muzei API 3.4.2 for wallpaper provider functionality
  - AndroidX WorkManager 2.10.1 for background tasks
  - Retrofit 3.0.0 + OkHttp for networking
  - Moshi 1.15.2 for JSON parsing with KSP code generation
  - Firebase Crashlytics 19.4.3 for error tracking
  - MockK 1.14.2 for unit testing

## Development Guidelines

### Code Style
- Follow Kotlin coding conventions
- Use KTX extensions where appropriate
- Lint with Kotlinter plugin (ktlint)
- Maintain consistent naming patterns across the codebase

### Testing
- Unit tests for business logic using MockK
- Test files located in `app/src/test/java/`
- Mock external dependencies (Firebase, Android APIs)

### Build & Release
- Debug builds: `./gradlew assembleDebug`
- Release builds: `./gradlew bundleRelease` (requires keystore setup)
- Lint: `./gradlew lintKotlin`
- Tests: `./gradlew test`

### Key Patterns
- Background work is handled through WorkManager with network constraints
- Images are loaded and provided to Muzei through ProviderContract
- Error tracking via Firebase Crashlytics
- Retrofit service is configured as a singleton object

## Important Notes
- The app fetches images from `https://vision.hossainkhan.com/` API
- Wallpapers change every 3 hours by default (configurable in Muzei)
- The provider authority is `com.hossainkhan.vision`
- Images are attributed to "H.K. Vision (vision.hossainkhan.com)"