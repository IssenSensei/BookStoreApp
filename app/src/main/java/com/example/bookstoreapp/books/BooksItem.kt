package com.example.bookstoreapp.books

import java.io.Serializable

data class BooksItem (
    var id: Int = 0,
    var title: String = "",
    var year: String = "",
    var print: String = "",
    var file: String = "",
    var picture: String = "",
    var description: String = "",
    var authorName: String = "",
    var authorSurname: String = "",
    var isbn: String = "",
    var categoryId: String = "",

    var expanded: Boolean = false

): Serializable

