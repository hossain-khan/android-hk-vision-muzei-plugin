package com.hossainkhan.vision.model

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

class VisionPhotosTest {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
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
            IsEqual(sut.source),
        )

        assertThat(
            "Site author mismatch",
            "Hossain Khan",
            IsEqual(sut.author),
        )

        assertThat(
            "Site copyright mismatch",
            "All rights reserved.",
            IsEqual(sut.copyright),
        )
    }

    @Test
    fun `given photos json - parses featured photos`() {
        assertThat(
            "Featured photos missing",
            20,
            IsEqual(sut.featuredPhotos.size),
        )
    }

    @Test
    fun `given photos json - parses blog photos`() {
        assertThat(
            "Featured photos missing",
            17,
            IsEqual(sut.blogPhotos.size),
        )
    }

    @Test
    fun `given photos json - parses fourth feature photo metadata`() {
        val photo = sut.featuredPhotos[3]

        assertThat(
            "Title mismatch",
            "⛰ Mountain Falls",
            IsEqual(photo.title),
        )
        assertThat(
            "Sub-title mismatch",
            "f: 4.5, t: 1/100s, l: 28mm, Sony RX-100",
            IsEqual(photo.subtitle),
        )
        assertThat(
            "Date mismatch",
            "2017-12-28 13:34:11",
            IsEqual(photo.date),
        )
        assertThat(
            "Web URL mismatch",
            "https://vision.hossainkhan.com/project/jamaica-mountain-falls",
            IsEqual(photo.webUrl),
        )
        assertThat(
            "Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/DSC_20171228_04001-jamaica-mountain-falls-1600x1100.jpg",
            IsEqual(photo.imageSource),
        )
        assertThat(
            "RAW Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/DSC_20171228_04001-jamaica-mountain-falls.jpg",
            IsEqual(photo.rawSource),
        )
    }

    @Test
    fun `given photos json - parses first feature photo metadata`() {
        val photo = sut.featuredPhotos[0]

        assertThat(
            "Title mismatch",
            "Love ❤ meter",
            IsEqual(photo.title),
        )
        assertThat(
            "Sub-title mismatch",
            "f: 5.6, t: 1/320s, l: 47mm, Canon REBEL XTi",
            IsEqual(photo.subtitle),
        )
        assertThat(
            "Date mismatch",
            "2010-09-05 14:20:56",
            IsEqual(photo.date),
        )
        assertThat(
            "Web URL mismatch",
            "https://vision.hossainkhan.com/project/love-meter",
            IsEqual(photo.webUrl),
        )
        assertThat(
            "Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/IMG_20100905_1857-love-meter-montreal-landscape-cropped-1600x1100.jpg",
            IsEqual(photo.imageSource),
        )
        assertThat(
            "RAW Image Source mismatch",
            "https://vision.hossainkhan.com/images/front-page/IMG_20100905_1857-love-meter-montreal-landscape-cropped-1600x1100.jpg",
            IsEqual(photo.rawSource),
        )
    }

    @Test
    fun `given first blog photo - parses metadata`() {
        val photo = sut.blogPhotos[0]

        assertThat(
            "Title mismatch",
            "Cusco City",
            IsEqual(photo.title),
        )
        assertThat(
            "Sub-title mismatch",
            "",
            IsEqual(photo.subtitle),
        )
        assertThat(
            "Date mismatch",
            "2020-01-21 13:48:00",
            IsEqual(photo.date),
        )
        assertThat(
            "Web URL mismatch",
            "https://vision.hossainkhan.com/blog/exploring-small-town",
            IsEqual(photo.webUrl),
        )
        assertThat(
            "Image Source mismatch",
            "https://vision.hossainkhan.com/images/2020-01/IMG_20200121_163328-PANO-01-cusco-hdr-1600x1100.jpg",
            IsEqual(photo.imageSource),
        )
        assertThat(
            "RAW Image Source mismatch",
            "https://vision.hossainkhan.com/images/2020-01/IMG_20200121_163328-PANO-01-cusco-hdr.jpg",
            IsEqual(photo.rawSource),
        )
    }
}
