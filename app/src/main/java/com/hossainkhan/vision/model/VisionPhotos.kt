package com.hossainkhan.vision.model

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
    val featured_photos: List<Photo> = emptyList()
)