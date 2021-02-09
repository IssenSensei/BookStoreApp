package com.issen.ebooker.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabasePdf(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val isAvailable: Boolean,
    val acsTokenLink: String?
)