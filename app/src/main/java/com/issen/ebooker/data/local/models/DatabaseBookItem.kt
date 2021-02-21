package com.issen.ebooker.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class DatabaseBookItem constructor(

    @ColumnInfo(name = "book_id")
    @PrimaryKey
    val bookId: String,
    val title: String,
    val authors: List<String>?,
    val publisher: String?,
    val description: String?,

    @ColumnInfo(name = "image_links_id")
    val imageLinksId: Int?
)


