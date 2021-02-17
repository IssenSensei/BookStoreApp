package com.issen.ebooker.data.local.models

import androidx.room.*

data class DatabaseReview constructor(

    @Embedded
    val reviewItem: DatabaseReviewItem,

    @Relation(
        parentColumn = "book_id",
        entityColumn = "book_id"
    )
    val book: DatabaseBook,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "user_id"
    )
    val user: DatabaseUser
)


