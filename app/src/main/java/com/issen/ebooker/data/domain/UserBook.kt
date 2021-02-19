package com.issen.ebooker.data.domain

import java.io.Serializable

data class UserBook(
    val bookId: String,
    val shelfId: String
): Serializable
