package com.hossainkhan.vision.data

import android.net.Uri
import android.util.Log
import androidx.work.ListenableWorker.Result
import com.google.android.apps.muzei.api.provider.ProviderClient
import com.google.android.apps.muzei.api.provider.ProviderContract
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.hossainkhan.vision.model.VisionPhotos
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HkVisionWorkerTest {
    private lateinit var sut: HkVisionWorker
    private val apiService: HkVisionApi = mockk()
    private lateinit var photos: VisionPhotos
    private val providerClient: ProviderClient = mockk(relaxed = true)

    @Before
    fun setUp() {
        mockkObject(HkVisionService)
        every { HkVisionService.api } returns apiService

        // Taken from https://github.com/mockk/mockk/issues/362
        mockkStatic(FirebaseCrashlytics::class)
        every { FirebaseCrashlytics.getInstance() } returns mockk(relaxed = true)
        mockkStatic(Log::class)
        every { Log.w(any(), any(), any()) } returns mockk(relaxed = true)
        every { Log.w(any(), ofType(Throwable::class)) } returns mockk(relaxed = true)
        every { Log.w(any(), ofType(String::class)) } returns mockk(relaxed = true)
        every { Log.d(any(), any()) } returns mockk(relaxed = true)
        mockkStatic(ProviderContract::class)
        every { ProviderContract.getProviderClient(any(), ofType(String::class)) } returns providerClient
        mockkStatic(Uri::class)
        every { Uri.parse(any()) } returns mockk(relaxed = true)

        val readText = HkVisionWorkerTest::class.java.getResource("/photos.json")!!.readText()
        val adapter: JsonAdapter<VisionPhotos> = Moshi.Builder().build().adapter(VisionPhotos::class.java)
        photos = adapter.fromJson(readText)!!

        sut = HkVisionWorker(context = mockk(), workerParams = mockk())
    }

    @Test
    fun `doWork - given photos api request fails - then work is re-queued`() {
        every { apiService.photos().execute() } throws IllegalStateException("API Request Failed")

        val result = sut.doWork()

        assertTrue(result is Result.Retry)
    }

    @Test
    fun `doWork - given photos api request success with no photo data - then work is re-queued`() {
        every { apiService.photos().execute().body() } returns VisionPhotos()

        val result = sut.doWork()

        assertTrue(result is Result.Retry)
    }

    @Test
    fun `doWork - given photos api request success with data - then work is success`() {
        every { apiService.photos().execute().body() } returns photos

        val result = sut.doWork()

        assertTrue(result is Result.Success)
    }
}
