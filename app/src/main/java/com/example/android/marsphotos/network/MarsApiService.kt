package com.example.android.marsphotos.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

// Retrofit builder to build and create a Retrofit object.
private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// defines how Retrofit talks to the web server using HTTP requests.
interface MarsApiService {


    // suspend:  So that you can call this method from within a coroutine.
    @GET("photos")
    suspend fun getPhotos(): String


}

//
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java) }
}