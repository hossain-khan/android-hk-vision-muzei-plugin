package com.hossainkhan.vision.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class VisionPhotosTest {

    private val moshi: Moshi = Moshi.Builder().build()
    private lateinit var sut: VisionPhotos

    @Before
    fun setUp() {
        val readText = VisionPhotosTest::class.java.getResource("/photos.json")!!.readText()

        val adapter: JsonAdapter<VisionPhotos> = moshi.adapter(VisionPhotos::class.java)

        sut = adapter.fromJson(readText)!!
    }


    @Test
    fun `given photos json - parses author information`() {
        assertThat(
            "Site source mismatch",
            "https://vision.hossainkhan.com/",
            IsEqual(sut.source)
        )

        assertThat(
            "Site author mismatch",
            "Hossain Khan",
            IsEqual(sut.author)
        )

        assertThat(
            "Site copyright mismatch",
            "All rights reserved.",
            IsEqual(sut.copyright)
        )
    }

    @Test
    fun `given photos json - parses featured photos`() {
        assertThat(
            "Featured photos missing",
            2,
            IsEqual(sut.featuredPhotos.size)
        )
    }

    @Test
    fun `given photos json - parses blog photos`() {
        assertThat(
            "Featured photos missing",
            2,
            IsEqual(sut.blogPhotos.size)
        )
    }
}