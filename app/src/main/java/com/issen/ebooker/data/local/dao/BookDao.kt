package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.local.models.DatabaseBook
import com.issen.ebooker.data.local.models.DatabaseBookItem

@Dao
interface BookDao {

    @Transaction
    @Query("select * from book_table")
    fun getBooks(): LiveData<List<DatabaseBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseBookItem: DatabaseBookItem)

    @Query("SELECT title from book_table where book_id = :bookId")
    suspend fun getBookTitle(bookId: String): String?

    @Transaction
    @Query("SELECT * FROM book_table where book_id in (SELECT book_id from user_books_table where user_id = :uid and shelf_id = :shelfId)")
    fun getShelfBooks(shelfId: Int, uid: String): LiveData<List<DatabaseBook>>

    @Transaction
    @Query("SELECT * FROM book_table WHERE publisher = :publisher")
    fun getPublisherBooks(publisher: String): LiveData<List<DatabaseBook>>

    @Transaction
    @Query("SELECT * FROM book_table WHERE authors like :author")
    fun getAuthorBooks(author: String): LiveData<List<DatabaseBook>>
}