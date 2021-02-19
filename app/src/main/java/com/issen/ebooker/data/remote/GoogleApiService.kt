package com.issen.ebooker.data.remote

import com.issen.ebooker.data.local.models.DatabaseUserBookItem
import com.issen.ebooker.data.remote.models.ResponseBookshelfList
import com.issen.ebooker.data.remote.models.ResponseVolumeList
import retrofit2.http.*

interface GoogleApiService {

    @GET("v1/volumes?q=intitle:flower")
    suspend fun getBooks(): ResponseVolumeList

    @GET("v1/mylibrary/bookshelves/{shelf}/volumes?")
    suspend fun getShelfBooks(
        @Path("shelf") shelfId: Int
    ): ResponseVolumeList

    @GET("v1/mylibrary/bookshelves")
    suspend fun getUserShelves(): ResponseBookshelfList

    @GET("v1/volumes?")
    suspend fun getQueriedBooks(@Query("q") queryString: String): ResponseVolumeList

    @POST("v1/mylibrary/bookshelves/{shelfId}/addVolume?")
    suspend fun addToUserShelf(
        @Path("shelfId") shelfId: Int,
        @Query("volumeId") volumeId: String
    )

    @POST("v1/mylibrary/bookshelves/{shelfId}/removeVolume?")
    suspend fun removeFromUserShelf(
        @Path("shelfId") shelfId: Int,
        @Query("volumeId") volumeId: String
    )


}