package com.example.devbytesapp.network

import com.example.devbytesapp.models.NetworkVideoContainer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DevbytesApi{
    @GET("devbytes")
    suspend fun getDevbytesList(): NetworkVideoContainer
}

object ApiService{
    val retrofitService: DevbytesApi by lazy {
        retrofit.create(DevbytesApi::class.java)
    }
}