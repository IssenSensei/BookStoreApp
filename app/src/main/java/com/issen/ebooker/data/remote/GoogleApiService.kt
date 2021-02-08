package com.issen.ebooker.data.remote

import com.issen.ebooker.data.remote.models.BooksApiResponse
import retrofit2.http.GET

interface GoogleApiService{

    @GET("v1/")
    suspend fun getBooks(): BooksApiResponse

}