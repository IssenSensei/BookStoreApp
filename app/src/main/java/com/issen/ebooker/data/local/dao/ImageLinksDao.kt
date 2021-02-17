package com.issen.ebooker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.issen.ebooker.data.local.models.DatabaseImageLinks

@Dao
interface ImageLinksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(imageLinks: DatabaseImageLinks): Long
}