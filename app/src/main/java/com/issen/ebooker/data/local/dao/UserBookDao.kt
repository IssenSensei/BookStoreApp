package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Query("SELECT EXISTS (SELECT * from user_books_table where book_id = :bookId and shelf_id = :shelfId and user_id = :uid)")
    fun checkIsOnShelf(bookId: String, shelfId: Int, uid: String): LiveData<Boolean>

    @Insert
    suspend fun addToShelf(databaseUserBookItem: DatabaseUserBookItem)

    @Delete
    suspend fun removeFromShelf(databaseUserBookItem: DatabaseUserBookItem)
}