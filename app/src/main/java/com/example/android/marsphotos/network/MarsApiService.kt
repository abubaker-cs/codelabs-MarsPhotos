package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


// Base URL (Hosting the RESTFUL API)
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

// To build and create a Retrofit object.
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// 1. Expose the service to the rest of the app using object declaration.
// 2. This is the public singleton object that can be accessed from the rest of the app.
object MarsApi {

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

}

// Defines how Retrofit talks to the web server using HTTP requests.
interface MarsApiService {

    // suspend:  So that you can call this method from within a coroutine.
    @GET("photos")
    suspend fun getPhotos(): String

}