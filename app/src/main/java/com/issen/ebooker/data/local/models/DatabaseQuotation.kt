package com.issen.ebooker.data.local.models

import androidx.room.*

data class DatabaseQuotation constructor(

    @Embedded
    val quotationItem: DatabaseQuotationItem,

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


