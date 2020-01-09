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
    fun getBooks(@Query("apicall") apicall: String,
                 @Query("userId") userId: Int
    )
            : Call<List<BooksItem>>

    @GET("Api.php")
    fun getAuthorBooks(
        @Query("apicall") apicall: String,
        @Query("authorName") authorName: String,
        @Query("authorSurname") authorSurname: String,
        @Query("id") id: Int

    )
            : Call<List<BooksItem>>

    @GET("Api.php")
    fun getPrintBooks(
        @Query("apicall") apicall: String,
        @Query("printName") printName: String,
        @Query("id") id: Int
    )
            : Call<List<BooksItem>>

    @GET("Api.php")
    fun getNews(@Query("apicall") apicall: String)
            : Call<List<NewsItem>>

    @GET("Api.php")
    fun getUserQuotes(
        @Query("apicall") apicall: String,
        @Query("userId") userId: Int
    )
            : Call<List<UserQuotesItem>>

    @GET("Api.php")
    fun getBookQuotes(
        @Query("apicall") apicall: String,
        @Query("userId") userId: Int
    ): Call<List<BookQuotesItem>>

    @GET("Api.php")
    fun getBookComments(
        @Query("apicall") apicall: String,
        @Query("bookId") bookId: Int
    ): Call<List<CommentItem>>

    @GET("Api.php")
    fun getUser(
        @Query("apicall") apicall: String,
        @Query("id") id: Int
    ): Call<UserItem>

    @FormUrlEncoded
    @POST("Api.php")
    fun addBookComment(
        @Query("apicall") apicall: String,
        @Field("content") content: String,
        @Field("rating") rating: Int,
        @Field("bookId") bookId: Int,
        @Field("userId") userId: Int
    )
            : Call<NewCommentItem>

    @FormUrlEncoded
    @POST("Api.php")
    fun addBookQuote(
        @Query("apicall") apicall: String,
        @Field("content") content: String,
        @Field("bookId") bookId: Int,
        @Field("userId") userId: Int
    )
            : Call<Int>

    @GET("Api.php")
    fun getUserId(
        @Query("apicall") apicall: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun createUser(
        @Query("apicall") apicall: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("surname") lastName: String,
        @Field("email") email: String
    )
            : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun deleteUser(
        @Query("apicall") apicall: String,
        @Field("userId") userId: Int
    )
            : Call<Boolean>

    @FormUrlEncoded
    @POST("Api.php")
    fun updateUser(
        @Query("apicall") apicall: String,
        @Field("userId") userId: Int,
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("email") email: String
    )
            : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun deleteBook(
        @Query("apicall") apicall: String,
        @Field("bookId") bookId: Int,
        @Field("userId") userId: Int
    )
            : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun deleteQuote(
        @Query("apicall") apicall: String,
        @Field("quoteId") quoteId: Int
    )
            : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun changePassword(
        @Query("apicall") apicall: String,
        @Field("password") password: String,
        @Field("newPassword") newPassword: String,
        @Field("userId") userId: Int
    )
            : Call<Int>

    @FormUrlEncoded
    @POST("Api.php")
    fun recoverPassword(
        @Query("apicall") apicall: String,
        @Field("password") password: String,
        @Field("email") email: String
    )
            : Call<Int>

    companion object {
        var USER_ID: Int = -1
        var BASE_URL = "http://165.22.90.206/BookstoreApi/api/"

        const val REGISTER_CODE = 1
        const val RECOVERY_CODE = 2
        const val AUTHOR_CODE = 3
        const val PRINT_CODE = 4
        const val BOOK_READER_CODE = 5
        const val photoPath = "http://165.22.90.206/storage/"

        fun create(): ApiInterface {

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