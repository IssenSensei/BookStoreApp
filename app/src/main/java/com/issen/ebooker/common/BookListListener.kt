package com.issen.ebooker.common

import com.issen.ebooker.data.domain.Book

interface BookListListener {
    fun onBookSelected(book: Book)
}