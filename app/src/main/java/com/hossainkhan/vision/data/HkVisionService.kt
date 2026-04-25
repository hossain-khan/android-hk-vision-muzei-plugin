package com.hossainkhan.vision.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Singleton service that provides the configured [HkVisionApi] Retrofit instance.
 *
 * Uses [OkHttpClient] for HTTP transport and [Moshi] with [KotlinJsonAdapterFactory]
 * for JSON deserialization. The base URL points to [https://vision.hossainkhan.com/].
 */
object HkVisionService {
    private const val BASE_URL = "https://vision.hossainkhan.com/"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    private val moshi: Moshi =
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val api: HkVisionApi by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(HkVisionApi::class.java)
    }
}
