package com.hossainkhan.vision.data

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.apps.muzei.api.provider.Artwork
import com.google.android.apps.muzei.api.provider.ProviderContract
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hossainkhan.vision.BuildConfig.HK_VISION_MUZEI_PROVIDER_AUTHORITY
import com.hossainkhan.vision.model.VisionPhotos

/**
 * Background worker that is used to load the images.
 *
 * References:
 * - [UnsplashExampleWorker.kt](https://github.com/romannurik/muzei/blob/master/example-unsplash/src/main/java/com/example/muzei/unsplash/UnsplashExampleWorker.kt)
 */
class HkVisionWorker constructor(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {
        private const val LOG_TAG = "HkVisionWorker"

        internal fun enqueueLoad(context: Context) {
            val workManager = WorkManager.getInstance(context)
            workManager.enqueue(
                OneTimeWorkRequestBuilder<HkVisionWorker>()
                    .setConstraints(
                        Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()
                    )
                    .build()
            )
        }
    }

    /**
     * Loads the photos and prepares the [Artwork]s.
     *
     * _NOTE: This method is called on a background thread._
     */
    override fun doWork(): Result {
        val visionPhotos: VisionPhotos? = try {
            FirebaseCrashlytics.getInstance().log("Loading HK Vision Photos")
            HkVisionService.api.photos().execute().body()
        } catch (error: Exception) {
            Log.w(LOG_TAG, "Error reading response", error)
            FirebaseCrashlytics.getInstance().recordException(error)
            return Result.retry()
        }

        if (visionPhotos == null || visionPhotos.featuredPhotos.isEmpty()) {
            Log.w(LOG_TAG, "Error reading response")
            FirebaseCrashlytics.getInstance().recordException(
                IllegalStateException("Photo response is invalid or empty: ${visionPhotos?.featuredPhotos?.size}")
            )
            return Result.retry()
        } else {
            FirebaseCrashlytics.getInstance().log("Found total photos: ${visionPhotos.featuredPhotos.size}")
            Log.d(LOG_TAG, "Found total photos: ${visionPhotos.featuredPhotos.size}")

            val providerClient = ProviderContract.getProviderClient(
                applicationContext,
                HK_VISION_MUZEI_PROVIDER_AUTHORITY
            )

            providerClient.addArtwork(
                visionPhotos.featuredPhotos
                    .plus(visionPhotos.blogPhotos)
                    .map { photo ->
                        Artwork(
                            token = photo.rawSource,
                            title = photo.title,
                            byline = photo.subtitle,
                            persistentUri = photo.rawSource.toUri(),
                            webUri = photo.webUrl.toUri(),
                            attribution = "H.K. Vision (vision.hossainkhan.com)"
                        )
                    })

            return Result.success()
        }

    }
}