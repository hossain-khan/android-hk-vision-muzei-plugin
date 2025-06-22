# Firewall Configuration for GitHub Copilot Coding Agent

# This file contains firewall rules needed for the Android HK Vision Muzei Plugin project
# to work properly with GitHub Copilot coding agent.

## Required Domains for Android Development

### Android Gradle Plugin and Google Maven Repository
# Required for Android Gradle Plugin downloads and Google/Android dependencies
dl.google.com

### Maven Central Repository
# Required for most open-source Java/Kotlin dependencies
repo1.maven.org
repo.maven.apache.org

### Gradle Dependencies
# Required for Gradle wrapper and plugin downloads
services.gradle.org
downloads.gradle.org
repo.gradle.org

### JetBrains Repository
# Required for Kotlin compiler and related plugins
maven.pkg.jetbrains.space
cache-redirector.jetbrains.com

### Additional Android Dependencies
# Other Google services that might be needed
maven.google.com
android.googlesource.com

### Firebase and Google Services
# Required for Firebase Crashlytics and Google Services plugin
firebase.google.com
console.firebase.google.com

### Project-Specific Domains
# The API endpoint for the H.K. Vision service
vision.hossainkhan.com

## Usage Notes

These domains should be allowed in the firewall configuration to ensure:
1. Gradle can download the Android Gradle Plugin from dl.google.com
2. Dependencies can be resolved from Maven Central and Google Maven
3. The application can access its backend API
4. Firebase services work correctly

## Security Considerations

- Only allow HTTPS traffic to these domains
- Consider using DNS filtering for additional security
- Monitor access logs for unusual activity
- Keep this list minimal and remove unused domains