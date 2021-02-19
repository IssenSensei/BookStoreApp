package com.issen.ebooker.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "review_table", primaryKeys = ["review_id", "user_id", "book_id"])
data class DatabaseReviewItem constructor(

    @ColumnInfo(name = "review_id")
    val reviewId: Int,
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "book_id")
    val bookId: String,

    val content: String?,
    val rating: Float
) : Serializable {
    constructor() : this(
        0,
        "",
        "",
        "",
        -1f
    )
}



