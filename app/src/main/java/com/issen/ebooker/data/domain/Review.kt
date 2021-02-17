package com.issen.ebooker.data.domain

data class Review(
    val reviewId: Int,
    val user: User,
    val book: Book,
    val content: String?,
    val rating: String
)
