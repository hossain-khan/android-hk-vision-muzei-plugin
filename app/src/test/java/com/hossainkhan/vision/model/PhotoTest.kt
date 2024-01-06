package com.hossainkhan.vision.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class PhotoTest {
    private val moshi: Moshi = Moshi.Builder().build()
    private lateinit var sut: Photo

    @Before
    fun setUp() {
        val readText = PhotoTest::class.java.getResource("/photo.json")!!.readText()

        val adapter: JsonAdapter<Photo> = moshi.adapter(Photo::class.java)

        sut = adapter.fromJson(readText)!!
    }

    @Test
    fun `given photo json - parses photo metadata`() {
        assertThat(
            "Title mismatch",
            "⛰ Mountain Falls",
            IsEqual(sut.title),
        )
        assertThat(
            "Sub-title mismatch",
            "f: 4.5, t: 1/100s, l: 28mm, Sony RX-100",
            IsEqual(sut.subtitle),
        )
        assertThat(
            "Date mismatch",
            "2017-12-28 13:34:11",
            IsEqual(sut.date),
        )
        assertThat(
            "Web URL mismatch",
            "https://vision.hossainkhan.com/project/jamaica-mountain-falls",
            IsEqual(sut.webUrl),
        )
        assertThat(
            "Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/DSC_20171228_04001-jamaica-mountain-falls-1600x1100.jpg",
            IsEqual(sut.imageSource),
        )
        assertThat(
            "RAW Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/DSC_20171228_04001-jamaica-mountain-falls.jpg",
            IsEqual(sut.rawSource),
        )
    }
}
