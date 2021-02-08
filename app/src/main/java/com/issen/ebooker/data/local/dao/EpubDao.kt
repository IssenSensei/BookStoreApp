package com.issen.ebooker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.issen.ebooker.data.local.models.DatabaseEpub

@Dao
interface EpubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(epub: DatabaseEpub): Int
}