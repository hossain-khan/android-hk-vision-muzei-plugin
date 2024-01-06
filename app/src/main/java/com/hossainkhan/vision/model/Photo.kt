package com.hossainkhan.vision.model

import com.squareup.moshi.Json

/**
 * Single photo object.
 *
 * For example:
 * ```
 * {
 *   "title": "Blue Sky",
 *   "subtitle": "f: 5.6, t: 1/320s, l: 47mm, Canon REBEL XTi",
 *   "date": "2010-09-05 14:20:56",
 *   "web_uri": "https://vision.hossainkhan.com/project/love-meter",
 *   "image_src": "https://vision.hossainkhan.com/images/front-page/IMG_20100905_1857-love-meter-montreal-landscape-cropped-1600x1100.jpg",
 *   "raw_src": "https://vision.hossainkhan.com/images/front-page/IMG_20100905_1857-love-meter-montreal-landscape-cropped.jpg"
 * }
 * ```
 */
data class Photo(
    @field:Json(name = "title")
    val title: String = "",
    @field:Json(name = "subtitle")
    val subtitle: String = "",
    @field:Json(name = "date")
    val date: String = "",
    @field:Json(name = "web_uri")
    val webUrl: String = "",
    @field:Json(name = "image_src")
    val imageSource: String = "",
    @field:Json(name = "raw_src")
    val rawSource: String = "",
)
