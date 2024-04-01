package com.hossainkhan.vision.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Root object for HK Vision photos.
 *
 * ```
 * {
 *    "source": "https://vision.hossainkhan.com/",
 *    "author": "Hossain Khan",
 *    "copyright": "All rights reserved.",
 *    "featured_photos": [ { ... }, { ... } ]
 * }
 * ```
 *
 * See [Photos.json](https://vision.hossainkhan.com/photos.json)
 */
@JsonClass(generateAdapter = true)
data class VisionPhotos(
    @field:Json(name = "source")
    val source: String = "",
    @field:Json(name = "author")
    val author: String = "",
    @field:Json(name = "copyright")
    val copyright: String = "",
    @field:Json(name = "featured_photos")
    val featuredPhotos: List<Photo> = emptyList(),
    @field:Json(name = "blog_photos")
    val blogPhotos: List<Photo> = emptyList(),
)
