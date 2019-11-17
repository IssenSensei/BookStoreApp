package com.example.bookstoreapp.userQuotes

import java.io.Serializable

data class UserQuotesItem (
    var id: Int = 0,
    var bookTitle: String = "",
    var content: String = "",
    var userName: String = "",

    var expanded: Boolean = false

): Serializable