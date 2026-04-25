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
    /**
     * Called by Muzei when it needs new artwork. Enqueues [HkVisionWorker] to fetch
     * photos from the remote API and register them with the provider.
     *
     * @param initial `true` when this is the first load after the provider was selected.
     */
    override fun onLoadRequested(initial: Boolean) {
        val context: Context? = context

        if (context == null) {
            FirebaseCrashlytics.getInstance().recordException(
                IllegalStateException("Missing context, unable to enqueue worker to load photos."),
            )
            return
        }

        FirebaseCrashlytics.getInstance().log("Enqueuing ${HkVisionWorker::class.java.simpleName} task.")
        HkVisionWorker.enqueueLoad(context)
    }
}
