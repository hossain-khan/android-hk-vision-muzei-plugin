package com.hossainkhan.vision.muzei

import android.content.Context
import com.google.android.apps.muzei.api.provider.MuzeiArtProvider
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hossainkhan.vision.data.HkVisionWorker

/**
 * Provides all the available wallpaper via [HkVisionWorker].
 *
 * Source was taken from:
 * https://github.com/muzei/muzei/blob/main/example-unsplash/src/main/java/com/example/muzei/unsplash/UnsplashExampleArtProvider.kt
 */
class HkVisionArtProvider : MuzeiArtProvider() {
    override fun onLoadRequested(initial: Boolean) {
        val context: Context? = context

        if (context == null) {
            FirebaseCrashlytics.getInstance().recordException(
                IllegalStateException("Missing context, unable to enqueue worker to load photos.")
            )
            return
        }

        FirebaseCrashlytics.getInstance().log("Enqueuing ${HkVisionWorker::class.java.simpleName} task.")
        HkVisionWorker.enqueueLoad(context)
    }
}