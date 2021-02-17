package com.issen.ebooker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.issen.ebooker.data.local.models.DatabasePdf

@Dao
interface PdfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pdf: DatabasePdf): Long
}