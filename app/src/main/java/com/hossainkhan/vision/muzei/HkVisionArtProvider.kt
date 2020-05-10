package com.hossainkhan.vision.muzei

import com.google.android.apps.muzei.api.provider.MuzeiArtProvider
import com.hossainkhan.vision.data.HkVisionWorker

class HkVisionArtProvider : MuzeiArtProvider() {
    override fun onLoadRequested(initial: Boolean) {
        val context = context ?: return
        HkVisionWorker.enqueueLoad(context)
    }
}