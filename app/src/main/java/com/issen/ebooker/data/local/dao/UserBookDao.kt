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
    fun insert(databaseUserBookItem: DatabaseUserBookItem)
}