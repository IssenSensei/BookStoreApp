package com.issen.ebooker.data.local.models

import androidx.room.*

@Entity(tableName = "user_books_table", primaryKeys = ["book_id", "shelf_id", "user_id"])
data class DatabaseUserBookItem constructor(

    @ColumnInfo(name = "book_id")
    val bookId: String,

    @ColumnInfo(name = "shelf_id")
    val shelfId: Int,

    @ColumnInfo(name = "user_id")
    val userId: String
)


