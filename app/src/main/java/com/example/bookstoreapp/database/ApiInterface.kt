package com.example.bookstoreapp.database

import com.example.bookstoreapp.bookQuotes.BookQuotesItem
import com.example.bookstoreapp.books.BooksItem
import com.example.bookstoreapp.comments.CommentItem
import com.example.bookstoreapp.comments.NewCommentItem
import com.example.bookstoreapp.news.NewsItem
import com.example.bookstoreapp.user.UserItem
import com.example.bookstoreapp.userQuotes.UserQuotesItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson




interface ApiInterface {

    @GET("Api.php")
    fun getBooks(@Query("apicall") apicall : String) : Call<List<BooksItem>>

    @GET("Api.php")
    fun getAuthorBooks(@Query("apicall") apicall : String,
                       @Query("authorName") authorName: String) : Call<List<BooksItem>>

    @GET("Api.php")
    fun getPublisherBooks(@Query("apicall") apicall : String,
                       @Query("publisherName") publisherName: String) : Call<List<BooksItem>>

    @GET("Api.php")
    fun getNews(@Query("apicall") apicall : String) : Call<List<NewsItem>>

    @GET("Api.php")
    fun getUserQuotes(@Query("apicall") apicall : String,
                      @Query("id") id: Long) : Call<List<UserQuotesItem>>

    @GET("Api.php")
    fun getBookQuotes(@Query("apicall") apicall : String,
                      @Query("id") id: Long) : Call<List<BookQuotesItem>>

    @GET("Api.php")
    fun getBookComments(@Query("apicall") apicall : String,
                      @Query("id") id: Long) : Call<List<CommentItem>>

    @GET("Api.php")
    fun getUserId(@Query("apicall") apicall : String,
                  @Query("login") login: String,
                  @Query("password") password: String) : Call<List<UserItem>>

    @GET("Api.php")
    fun getUser(@Query("apicall") apicall : String,
                @Query("id") id: Long) : Call<UserItem>

    @GET("Api.php")
    fun getEmail(@Query("apicall") apicall : String,
                @Query("login") login: String)
                : Call<String>

    @FormUrlEncoded
    @POST("Api.php")
    fun addBookComment(@Query("apicall") apicall : String,
                    @Field("content") content: String,
                    @Field("rating") rating: Int,
                    @Field("bookId") bookId: Int,
                    @Field("userId") userId: Int)
                        : Call<NewCommentItem>

    @FormUrlEncoded
    @POST("Api.php")
    fun addBookQuote(@Query("apicall") apicall : String,
                    @Field("content") content: String,
                    @Field("bookId") bookId: Int,
                    @Field("userId") userId: Int)
                        : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun createUser(@Query("apicall") apicall : String,
                   @Field("login") login: String,
                   @Field("password") password: String,
                   @Field("name") name: String,
                   @Field("lastName") lastName: String,
                   @Field("email") email: String,
                   @Field("phone") phone: String,
                   @Field("status") status: String)
                    : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun updateUser(@Query("apicall") apicall : String,
                   @Field("userId") userId: Int,
                   @Field("login") login: String,
                   @Field("name") name: String,
                   @Field("lastName") lastName: String,
                   @Field("email") email: String,
                   @Field("phone") phone: String)
                    : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun deleteBook(@Query("apicall") apicall : String,
                   @Field("bookId") bookId: Int,
                   @Field("userId") userId: Int)
                    : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun deleteQuote(@Query("apicall") apicall : String,
                   @Field("quoteId") quoteId: String)
                    : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun changePassword(@Query("apicall") apicall : String,
                       @Field("password") password: String,
                       @Field("newPassword") newPassword: String,
                        @Field("userId") userId: Int)
                        : Call<Int>

    companion object {
        var USER_ID : Int = -1
        var BASE_URL = "http://192.168.0.164/BookstoreApi/api/"

        fun create() : ApiInterface {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
    }
}