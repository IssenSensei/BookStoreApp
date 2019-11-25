package com.example.bookstoreapp.database

import com.example.bookstoreapp.bookQuotes.BookQuotesItem
import com.example.bookstoreapp.books.BooksItem
import com.example.bookstoreapp.news.NewsItem
import com.example.bookstoreapp.user.UserItem
import com.example.bookstoreapp.userQuotes.UserQuotesItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {

    @GET("Api.php")
    fun getBooks(@Query("apicall") apicall : String) : Call<List<BooksItem>>

    @GET("Api.php")
    fun getUsers(@Query("apicall") apicall : String) : Call<List<UserItem>>

    @GET("Api.php")
    fun getNews(@Query("apicall") apicall : String) : Call<List<NewsItem>>

    @GET("Api.php")
    fun getUserQuotes(@Query("apicall") apicall : String,
                      @Query("id") id: Long) : Call<List<UserQuotesItem>>

    @GET("Api.php")
    fun getBookQuotes(@Query("apicall") apicall : String,
                      @Query("id") id: Long) : Call<List<BookQuotesItem>>

    @GET("Api.php")
    fun getUserId(@Query("apicall") apicall : String,
                  @Query("login") login: String,
                  @Query("password") password: String) : Call<List<UserItem>>

    @GET("Api.php")
    fun getUser(@Query("apicall") apicall : String,
                @Query("id") id: Long) : Call<List<UserItem>>

    @POST("Api.php")
    fun createUser(@Query("apicall") apicall : String,
                @Field("id") id: Long) : Call<List<UserItem>>



//    val URL_CREATE_USER = ROOT_URL + "createUser"
//    val URL_DELETE_USER = ROOT_URL + "deleteUser&id="
//    val URL_UPDATE_USER = ROOT_URL + "updateUser"
//    val URL_ADD_USER_QUOTE = ROOT_URL + "addUserQuote"
//    val URL_CHANGE_PASSWORD = ROOT_URL + "changePassword"

    companion object {

        var BASE_URL = "http://192.168.43.84/BookstoreApi/api/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }
}