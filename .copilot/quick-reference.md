# Quick Reference - HK Vision Muzei Plugin

## Essential Commands

```bash
# Build and lint
./gradlew lintKotlin build

# Run tests
./gradlew test

# Debug build and install
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk

# Release build
./gradlew bundleRelease
```

## Project Structure

```
app/src/main/java/com/hossainkhan/vision/
├── data/HkVisionWorker.kt      # Background image fetcher
├── data/HkVisionService.kt     # HTTP client singleton  
├── data/HkVisionApi.kt         # Retrofit API interface
├── model/Photo.kt              # Image data model
├── model/VisionPhotos.kt       # Image collection model
└── muzei/HkVisionArtProvider.kt # Muzei provider
```

## Key Dependencies

- **Muzei API**: 3.4.2 (wallpaper provider)
- **WorkManager**: 2.10.1 (background tasks)
- **Retrofit**: 3.0.0 (HTTP client)
- **Moshi**: 1.15.2 (JSON parsing)
- **Firebase Crashlytics**: 19.4.3 (error tracking)

## Important Notes

- Min SDK: 21 (Android 5.0+)
- Target SDK: 35 (Android 14+)
- Java 17 toolchain required
- Uses KSP for Moshi code generation
- Network-constrained background work
- 3-hour default wallpaper rotation

## API Integration

**Base URL**: `https://vision.hossainkhan.com/`
**Endpoint**: `/photos` (returns `VisionPhotos` JSON)

## Muzei Integration

**Provider Authority**: `com.hossainkhan.vision`
**Attribution**: "H.K. Vision (vision.hossainkhan.com)"