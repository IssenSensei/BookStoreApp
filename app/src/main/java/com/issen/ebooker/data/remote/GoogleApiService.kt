package com.issen.ebooker.data.remote

import com.issen.ebooker.data.remote.models.ResponseBookshelfList
import com.issen.ebooker.data.remote.models.ResponseVolumeList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleApiService {

    @GET("v1/volumes?q=intitle:flower")
    suspend fun getBooks(): ResponseVolumeList

    @GET("v1/mylibrary/bookshelves/{shelf}/volumes?")
    suspend fun getShelfBooks(
        @Header("Authorization") token: String,
        @Path("shelf") shelfId: Int
    ): ResponseVolumeList

    @GET("v1/mylibrary/bookshelves")
    suspend fun getUserShelves(@Header("Authorization") token: String): ResponseBookshelfList

    @GET("v1/volumes?")
    suspend fun getQueriedBooks(@Query("q") queryString: String, @Header("Authorization") token: String): ResponseVolumeList



}