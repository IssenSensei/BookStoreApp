package com.example.bookstoreapp.bookQuotes

import java.io.Serializable

data class BookQuotesItem (
    var id: Int = -1,
    var bookTitle: String = "",
    var content: String = "",
    var userName: String = "",
    var picture: String = "",

    var expanded: Boolean = false

): Serializable