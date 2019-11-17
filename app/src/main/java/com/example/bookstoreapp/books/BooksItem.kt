package com.example.bookstoreapp.books

import java.io.Serializable

data class BooksItem (
    var id: Int = 0,
    var title: String = "",
    var year: String = "",
    var publisher: String = "",
    var file: String = "",
    var picture: String = "",
    var description: String = "",
    var idAuthor: String = "",

    var expanded: Boolean = false

): Serializable

