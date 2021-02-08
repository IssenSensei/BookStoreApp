package com.issen.ebooker.data.remote.models

data class BooksApiResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Volume>
)


