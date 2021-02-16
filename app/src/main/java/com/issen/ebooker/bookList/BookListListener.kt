package com.issen.ebooker.bookList

import com.issen.ebooker.data.domain.Book

interface BookListListener {
    fun onBookSelected(book: Book)
}