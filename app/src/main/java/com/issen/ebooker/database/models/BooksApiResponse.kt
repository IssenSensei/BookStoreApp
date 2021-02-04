package com.issen.ebooker.database.models

data class BooksApiResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>,
    val volumeInfo: VolumeInfo
)
