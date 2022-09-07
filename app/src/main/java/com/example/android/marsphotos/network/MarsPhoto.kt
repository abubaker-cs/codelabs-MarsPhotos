package com.example.android.marsphotos.network

import com.squareup.moshi.Json

// To match the types in our specific JSON response, you use String objects for all the values.
data class MarsPhoto(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
)