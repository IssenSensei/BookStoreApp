package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.local.models.DatabaseBook
import com.issen.ebooker.data.local.models.DatabaseBookItem

@Dao
interface BookDao {

    @Transaction
    @Query("select * from book_table")
    fun getBooks(): LiveData<List<DatabaseBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseBookItem: DatabaseBookItem)
}