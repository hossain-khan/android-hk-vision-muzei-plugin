# Copilot Instructions for HK Vision Muzei Plugin

## Code Assistance Guidelines

When helping with this Android Muzei plugin project, please follow these guidelines:

### 1. Android Development Best Practices
- Use modern Android architecture patterns (MVVM where applicable)
- Prefer AndroidX libraries over legacy support libraries
- Follow Material Design guidelines for any UI components
- Use lifecycle-aware components (WorkManager, etc.)

### 2. Kotlin Conventions
- Use data classes for model objects
- Leverage Kotlin null safety features
- Use coroutines for asynchronous operations when appropriate
- Follow Kotlin naming conventions (camelCase for functions/properties)

### 3. Project-Specific Patterns
- **API Integration**: Use Retrofit with Moshi for JSON parsing
- **Background Tasks**: Use WorkManager with network constraints
- **Error Handling**: Log errors to Firebase Crashlytics
- **Testing**: Mock external dependencies using MockK

### 4. Code Quality
- Add appropriate error handling and logging
- Include documentation for public APIs
- Follow the existing code style and patterns
- Ensure thread safety for background operations

### 5. Common Tasks
- **Adding new API endpoints**: Extend `HkVisionApi` interface
- **Background processing**: Modify `HkVisionWorker` class
- **Data models**: Create/update classes in `model` package
- **Testing**: Add unit tests with MockK in `test` directory

### 6. Dependencies
- When suggesting new dependencies, prefer AndroidX over legacy
- Check compatibility with current min SDK (API 21)
- Consider APK size impact for new libraries
- Use the same version management pattern as existing dependencies

### 7. Build Configuration
- Gradle changes should maintain compatibility with AGP 8.10.1
- Test changes with `./gradlew lintKotlin build test`
- Ensure release builds still work with existing signing configuration

### 8. File Organization
```
app/src/main/java/com/hossainkhan/vision/
├── data/           # API clients, workers, data sources
├── model/          # Data models and DTOs
├── muzei/          # Muzei provider implementation
└── HkVisionRedirectActivity.kt  # Launcher activity
```