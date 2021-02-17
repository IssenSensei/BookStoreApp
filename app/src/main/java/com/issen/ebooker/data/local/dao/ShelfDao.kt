package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.local.models.DatabaseShelf

@Dao
interface ShelfDao {

    @Transaction
    @Query("select * from shelf_table")
    fun getShelves(): LiveData<List<DatabaseShelf>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseShelf: DatabaseShelf)
}