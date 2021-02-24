package com.issen.ebooker.data.domain

import java.io.Serializable

data class Review(
    val reviewId: String = "",
    val user: String = "",
    val book: String = "",
    val content: String? ="",
    val rating: Float = 0f
): Serializable
