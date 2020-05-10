package com.hossainkhan.vision.model

import com.squareup.moshi.Json

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
 */
data class VisionPhotos(
    val source: String = "",
    val author: String = "",
    val copyright: String = "",
    @Json(name = "featured_photos")
    val featuredPhotos: List<Photo> = emptyList()
)