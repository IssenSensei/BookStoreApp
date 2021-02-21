package com.issen.ebooker.data.local.models

import androidx.room.*

data class DatabaseBook constructor(

    @Embedded
    val databaseBookItem: DatabaseBookItem,

    @Relation(
        parentColumn = "image_links_id",
        entityColumn = "id"
    )
    val databaseImageLinks: DatabaseImageLinks?
)


