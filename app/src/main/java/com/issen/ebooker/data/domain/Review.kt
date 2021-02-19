package com.issen.ebooker.data.domain

import java.io.Serializable

data class Review(
    val reviewId: Int,
    val user: User,
    val book: Book,
    val content: String?,
    val rating: Float
): Serializable
