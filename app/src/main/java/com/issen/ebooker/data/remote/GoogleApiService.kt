package com.issen.ebooker.data.remote

import com.issen.ebooker.data.remote.models.BooksApiResponse
import retrofit2.http.GET

interface GoogleApiService{

    @GET("v1/volumes?q=intitle:flower")
    suspend fun getBooks(): BooksApiResponse

}