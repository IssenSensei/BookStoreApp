package com.issen.ebooker.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val okHttpClient = OkHttpClient.Builder()
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/books/")
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


object GoogleApiNetwork {
    val googleApi: GoogleApiService by lazy { retrofit.create(GoogleApiService::class.java) }
}