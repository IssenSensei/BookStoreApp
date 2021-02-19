package com.issen.ebooker.data.domain

import java.io.Serializable

data class Quotation(
    val quotationId: Int,
    val content: String,
    val book: Book,
    val user: User
): Serializable
