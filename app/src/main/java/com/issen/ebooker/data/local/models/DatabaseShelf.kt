package com.issen.ebooker.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shelf_table", primaryKeys = ["shelf_id", "user_id"])
data class DatabaseShelf constructor(

    @ColumnInfo(name = "shelf_id")
    val shelfId: Int,

    @ColumnInfo(name = "user_id")
    val userId: String,
    val title: String,
    val volumeCount: Int?
)


