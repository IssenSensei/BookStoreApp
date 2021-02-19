package com.issen.ebooker.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotation_table")
data class DatabaseQuotationItem constructor(

    @ColumnInfo(name = "quotation_id")
    @PrimaryKey(autoGenerate = true)
    val quotationId: Int = 0,

    val content: String = "",

    @ColumnInfo(name = "book_id")
    val bookId: String = "",

    @ColumnInfo(name = "user_id")
    val userId: String = ""
)


