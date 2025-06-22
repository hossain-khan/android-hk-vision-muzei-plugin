# Project Context for GitHub Copilot

## File Structure Overview

```
android-hk-vision-muzei-plugin/
├── .copilot/                      # Copilot configuration files
│   ├── agent.md                   # Project overview and architecture
│   ├── instructions.md            # Coding guidelines for Copilot
│   ├── firewall-rules.md          # Firewall configuration documentation
│   ├── firewall-config.txt        # Detailed firewall rules
│   └── setup-firewall.sh          # Automated firewall setup script
├── .github/workflows/             # CI/CD workflows
│   ├── android.yml                # Main Android CI pipeline
│   └── validations.yml            # Additional validation checks
├── app/                           # Main Android application module
│   ├── src/main/java/com/hossainkhan/vision/
│   │   ├── data/                  # Data layer components
│   │   │   ├── HkVisionApi.kt     # Retrofit API interface
│   │   │   ├── HkVisionService.kt # HTTP client singleton
│   │   │   └── HkVisionWorker.kt  # Background worker
│   │   ├── model/                 # Data models
│   │   │   ├── Photo.kt           # Individual photo model
│   │   │   └── VisionPhotos.kt    # Photo collection model
│   │   ├── muzei/                 # Muzei integration
│   │   │   └── HkVisionArtProvider.kt # Muzei provider
│   │   └── HkVisionRedirectActivity.kt # Launcher activity
│   ├── src/test/                  # Unit tests
│   │   ├── java/                  # Test source files
│   │   └── resources/             # Test data (JSON files)
│   └── build.gradle               # App module build configuration
├── gradle/                        # Gradle wrapper files
├── build.gradle                   # Root build configuration
├── settings.gradle                # Gradle settings
├── README.md                      # Project documentation
├── PRIVACY-POLICY.md              # Privacy policy
└── LICENSE                        # Apache 2.0 license
```

## Key Components Deep Dive

### Data Layer (`app/src/main/java/com/hossainkhan/vision/data/`)

1. **HkVisionApi.kt**
   - Retrofit interface defining API endpoints
   - Single method: `photos()` returning `Call<VisionPhotos>`
   - Uses synchronous calls (`.execute()`) in worker

2. **HkVisionService.kt**
   - Singleton object providing configured Retrofit instance
   - Uses OkHttpClient with default configuration
   - Moshi JSON converter with Kotlin reflection
   - Base URL: `https://vision.hossainkhan.com/`

3. **HkVisionWorker.kt**
   - Extends `Worker` from AndroidX WorkManager
   - Fetches photos from API and provides to Muzei
   - Network-constrained execution
   - Comprehensive error handling with Crashlytics

### Model Layer (`app/src/main/java/com/hossainkhan/vision/model/`)

1. **Photo.kt**
   - Data class representing individual photo
   - Properties: `title`, `subtitle`, `rawSource`, `webUrl`
   - Used with Moshi for JSON parsing

2. **VisionPhotos.kt**
   - Container for photo collections
   - Properties: `featuredPhotos`, `blogPhotos` (both List<Photo>)
   - Combines both types for Muzei artwork

### Muzei Integration (`app/src/main/java/com/hossainkhan/vision/muzei/`)

1. **HkVisionArtProvider.kt**
   - Extends `MuzeiArtProvider` from Muzei API
   - Triggers `HkVisionWorker` when load is requested
   - Handles context null-safety with Crashlytics logging

## Build Configuration

### Root `build.gradle`
- Android Gradle Plugin 8.10.1
- Kotlin 2.1.21
- Kotlinter plugin for linting
- Firebase and Google Services plugins

### App `build.gradle`
- Target SDK 35, Min SDK 21
- Java 17 toolchain
- KSP for Moshi code generation
- Signing configuration with keystore
- Build config fields for provider authority

## Development Workflow

1. **Linting**: `./gradlew lintKotlin`
2. **Build**: `./gradlew build`
3. **Test**: `./gradlew test`
4. **Debug Install**: `./gradlew assembleDebug && adb install app/build/outputs/apk/debug/app-debug.apk`
5. **Release**: `./gradlew bundleRelease`

## Testing Strategy

- Unit tests use MockK for mocking
- Firebase and Android APIs are mocked
- Test data in JSON files under `src/test/resources/`
- Focus on business logic in workers and data classes

## Important Patterns

- Singleton service objects for HTTP clients
- Background work with network constraints
- Error tracking via Firebase Crashlytics
- Kotlin null safety throughout
- Retrofit with synchronous calls in background workers