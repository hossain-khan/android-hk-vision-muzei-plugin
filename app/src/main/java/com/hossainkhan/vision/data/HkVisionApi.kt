package com.hossainkhan.vision.data

import com.hossainkhan.vision.model.VisionPhotos
import retrofit2.Call
import retrofit2.http.GET

/**
 * Access the photos using retrofit.
 *
 * https://square.github.io/retrofit/
 */
interface HkVisionApi {
    @GET("photos.json")
    fun photos(): Call<VisionPhotos>
}
