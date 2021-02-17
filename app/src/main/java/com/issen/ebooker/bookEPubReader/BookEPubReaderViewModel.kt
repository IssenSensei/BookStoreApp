package com.issen.ebooker.bookEPubReader

import androidx.lifecycle.ViewModel
import com.issen.ebooker.data.BooksRepository

class BookEPubReaderViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    lateinit var bookLink: String
}