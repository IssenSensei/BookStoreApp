package com.issen.ebooker.data.local.models

import androidx.room.*

@Entity(tableName = "user_table")
data class DatabaseUser constructor(

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "user_name")
    val userName: String
)


