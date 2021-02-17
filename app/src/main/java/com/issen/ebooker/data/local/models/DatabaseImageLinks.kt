package com.issen.ebooker.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_links_table")
data class DatabaseImageLinks(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val smallThumbnail: String?,
    val thumbnail: String?
)

