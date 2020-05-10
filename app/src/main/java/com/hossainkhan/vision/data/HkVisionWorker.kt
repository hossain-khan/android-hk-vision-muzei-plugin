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
import com.google.android.apps.muzei.api.provider.MuzeiArtProvider
import com.google.android.apps.muzei.api.provider.ProviderClient
import com.google.android.apps.muzei.api.provider.ProviderContract
import com.hossainkhan.vision.model.VisionPhotos

/**
 * Background worker that is used to load the images.
 *
 * References:
 * - [UnsplashExampleWorker.kt](https://github.com/romannurik/muzei/blob/master/example-unsplash/src/main/java/com/example/muzei/unsplash/UnsplashExampleWorker.kt)
 */
class HkVisionWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {


    companion object {
        private const val LOG_TAG = "HkVisionWorker"

        /**
         * The authority for the [MuzeiArtProvider] is needed a [ProviderClient] for.
         */
        private const val MUZEI_PROVIDER_AUTHORITY = "com.hossainkhan.vision"

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
            HkVisionService.api.photos().execute().body()
        } catch (e: Exception) {
            Log.w(LOG_TAG, "Error reading response", e)
            return Result.retry()
        }

        if (visionPhotos == null || visionPhotos.featuredPhotos.isEmpty()) {
            Log.w(LOG_TAG, "Error reading response")
            return Result.retry()
        } else {
            Log.d(LOG_TAG, "Found total photos: ${visionPhotos.featuredPhotos.size}")

            val providerClient = ProviderContract.getProviderClient(
                applicationContext,
                MUZEI_PROVIDER_AUTHORITY
            )

            providerClient.addArtwork(visionPhotos.featuredPhotos.map { photo ->
                Artwork().apply {
                    token = photo.rawSource
                    title = photo.title
                    byline = photo.subtitle
                    persistentUri = photo.rawSource.toUri()
                    webUri = photo.webUrl.toUri()
                }
            })

            return Result.success()
        }

    }
}