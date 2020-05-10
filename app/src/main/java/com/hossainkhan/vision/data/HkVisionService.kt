package com.hossainkhan.vision.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object HkVisionService {
    private const val BASE_URL = "https://vision.hossainkhan.com/"

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    val api: HkVisionApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HkVisionApi::class.java)
    }
}