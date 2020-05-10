package com.hossainkhan.vision.data

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.hossainkhan.vision.model.VisionPhotos

class HkVisionWorker(
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

    override fun doWork(): Result {
        val visionPhotos: VisionPhotos? = try {
            HkVisionService.api.photos().execute().body()
        } catch (e: Exception) {
            Log.w(LOG_TAG, "Error reading response", e)
            return Result.retry()
        }

        if (visionPhotos == null) {
            Log.w(LOG_TAG, "Error reading response")
            return Result.retry()
        } else {
            Log.d(LOG_TAG, "Found photos: ${visionPhotos.featured_photos}")
            return Result.success()
        }

    }
}