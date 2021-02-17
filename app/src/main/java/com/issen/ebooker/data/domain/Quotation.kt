package com.issen.ebooker.data.domain

data class Quotation(
    val quotationId: Int,
    val content: String,
    val book: Book,
    val user: User
)
