package com.example.android.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto(

    // ID
    val id: String,

    // Image URL
    @Json(name = "img_src") val imgSrcUrl: String
)