package com.example.bookstoreapp.news

import java.io.Serializable

data class NewsItem (
    var id: Int = -1,
    var content: String = "",
    var title: String = "",
    var login: String = "",
    var picture: String = "",

    var expanded: Boolean = false

): Serializable

