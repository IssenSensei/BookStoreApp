package com.issen.ebooker.data.local.models

import androidx.room.*

data class DatabaseBook constructor(

    @Embedded
    val databaseBookItem: DatabaseBookItem,

    @Relation(
        parentColumn = "pdf_id",
        entityColumn = "id"
    )
    val databasePdf: DatabasePdf,

    @Relation(
        parentColumn = "epub_id",
        entityColumn = "id"
    )
    val databaseEpub: DatabaseEpub,

    @Relation(
        parentColumn = "image_links_id",
        entityColumn = "id"
    )
    val databaseImageLinks: DatabaseImageLinks
)


