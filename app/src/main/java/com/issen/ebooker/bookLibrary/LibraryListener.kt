package com.issen.ebooker.bookLibrary

import com.issen.ebooker.data.domain.Book

interface LibraryListener {
    fun onBookSelected(book: Book)
    fun onShelfSelected(shelfId: Int)
}