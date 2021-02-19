package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.local.models.DatabaseUserBookItem

@Dao
interface UserBookDao {

    @Transaction
    @Query("select * from user_books_table")
    fun getUserBooks(): LiveData<List<DatabaseUserBookItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseUserBookItem: DatabaseUserBookItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun refreshShelfBooks(bookList: MutableList<DatabaseUserBookItem>)

    @Query("SELECT EXISTS (SELECT * from user_books_table where book_id = :bookId and shelf_id = :favouriteShelfId and user_id = :uid)")
    suspend fun checkIsFavourite(bookId: String, favouriteShelfId: Int, uid: String): Boolean

    @Insert
    suspend fun addToFavourites(databaseUserBookItem: DatabaseUserBookItem)

    @Delete
    suspend fun removeFromFavourites(databaseUserBookItem: DatabaseUserBookItem)
}