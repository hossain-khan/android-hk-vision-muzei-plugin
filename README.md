[![Android CI](https://github.com/hossain-khan/android-hk-vision-muzei-plugin/actions/workflows/android.yml/badge.svg)](https://github.com/hossain-khan/android-hk-vision-muzei-plugin/actions/workflows/android.yml) [![CodeFactor](https://www.codefactor.io/repository/github/hossain-khan/android-hk-vision-muzei-plugin/badge)](https://www.codefactor.io/repository/github/hossain-khan/android-hk-vision-muzei-plugin) [![Muzei.co](https://img.shields.io/badge/muzei.co-API%203.4%2B-blue)](http://api.muzei.co/)

# HK Vision - Muzei Plugin
Muzei wallpaper source plugin for [vision.hossainkhan.com](https://vision.hossainkhan.com/) site.

## Background
I wrote a quick article behind creating this plugin. [Read more about it at Medium.com](https://medium.com/@hossainkhan/hackathon-creating-the-simplest-muzei-wallpaper-plugin-for-android-9d080dbb4bf)

## How to use
First, install the [Muzei Live Wallpaper](https://play.google.com/store/apps/details?id=net.nurik.roman.muzei) Android app from Google Play store.  
[![Muzei-on-Google-Play](https://user-images.githubusercontent.com/99822/81494196-decc1600-9274-11ea-9296-f167952e5fc1.png)](https://play.google.com/store/apps/details?id=net.nurik.roman.muzei)

Next, install [Vision Muzei Plugin app](https://play.google.com/store/apps/details?id=com.hossainkhan.vision) from Google Play.

[<img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="258" height="100" alt="Google Play 646x250">](https://play.google.com/store/apps/details?id=com.hossainkhan.vision)

Once installed, go back to **Muzei** app, then the source should show up. Choose the wallpaper you like for your phone. Enjoy.

> _NOTE: By default, the walpaper changes **every 3 hours**, you may want to change it to you liking. For example every day :-)_

[![android-muzei-plugin-demo-large-small](https://user-images.githubusercontent.com/99822/81618324-19d56300-93b5-11ea-8367-62376439a99c.png)](https://play.google.com/store/apps/details?id=com.hossainkhan.vision)

## Dev Note
To build and install locally, use following command:

```
adb uninstall com.hossainkhan.vision && \
./gradlew clean assembleDebug && \
adb install app/build/outputs/apk/debug/app-debug.apk 
```

### New Release
```
./gradlew bundleRelease
```
> Make sure you have `keystore.properties` and `upload-keystore.jks` available in the project.

## GitHub Copilot Coding Agent Setup

This project is configured for use with [GitHub Copilot coding agent](https://docs.github.com/en/copilot/customizing-copilot/customizing-the-development-environment-for-copilot-coding-agent). The configuration files are located in the `.copilot/` directory:

- **`.copilot/agent.md`** - Project overview and architecture documentation
- **`.copilot/instructions.md`** - Copilot-specific coding guidelines and patterns
- **`.copilot/firewall-rules.md`** - Required firewall configuration documentation
- **`.copilot/firewall-config.txt`** - Detailed firewall rules for system administrators
- **`.copilot/setup-firewall.sh`** - Automated firewall setup script for Linux

### Firewall Configuration

The project requires access to the following domains for building and development:

- `dl.google.com` - Android Gradle Plugin and Google Maven Repository
- `repo1.maven.org` - Maven Central Repository
- `services.gradle.org` - Gradle services
- `vision.hossainkhan.com` - H.K. Vision API backend

See `.copilot/firewall-rules.md` for the complete list and setup instructions.

# References
* https://muzei.co/
* https://api.muzei.co/
* https://github.com/muzei/muzei/releases [API 3.4.x](https://github.com/muzei/muzei/releases/tag/api3.4.2)
* https://medium.com/muzei/muzei-3-0-and-the-new-api-4fd3d6133db6
* https://medium.com/muzei/announcing-muzei-live-wallpaper-3-0-d167dd5795a4
