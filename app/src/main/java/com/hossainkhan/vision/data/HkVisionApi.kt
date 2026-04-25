package com.hossainkhan.vision.data

import com.hossainkhan.vision.model.VisionPhotos
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit API interface for fetching HK Vision photos.
 *
 * https://square.github.io/retrofit/
 */
interface HkVisionApi {
    /**
     * Fetches the complete list of photos from the remote JSON endpoint.
     *
     * @return a [Call] wrapping the deserialized [VisionPhotos] response.
     */
    @GET("photos.json")
    fun photos(): Call<VisionPhotos>
}
