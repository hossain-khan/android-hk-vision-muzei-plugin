# HK Vision - Muzei Plugin
Muzei wallpaper source plugin for [vision.hossainkhan.com](https://vision.hossainkhan.com/) site.

## How to use
Install [Muzei app](https://play.google.com/store/apps/details?id=net.nurik.roman.muzei) from Google Play.
[![Muzei-on-Google-Play](https://user-images.githubusercontent.com/99822/81494196-decc1600-9274-11ea-9296-f167952e5fc1.png)](https://play.google.com/store/apps/details?id=net.nurik.roman.muzei)

Next, install [Vision Muzei Plugin app](https://play.google.com/store/apps/details?id=com.hossainkhan.vision) from Google Play.

Once installed, go back to **Muzei** app, then the source should show up. Choose the wallpaper you like for your phone. Enjoy.

[![android-muzei-plugin-demo-large-small](https://user-images.githubusercontent.com/99822/81618324-19d56300-93b5-11ea-8367-62376439a99c.png)](https://play.google.com/store/apps/details?id=com.hossainkhan.vision)

## Dev Note
To build and install locally, use following command:

```
./gradlew clean assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk 
```

# References
* https://muzei.co/
* https://github.com/romannurik/muzei/releases/tag/api3.2.0
* https://medium.com/muzei/muzei-3-0-and-the-new-api-4fd3d6133db6
* https://medium.com/muzei/announcing-muzei-live-wallpaper-3-0-d167dd5795a4
