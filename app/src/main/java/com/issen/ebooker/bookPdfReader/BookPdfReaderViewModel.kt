package com.issen.ebooker.bookPdfReader

import androidx.lifecycle.ViewModel
import com.issen.ebooker.data.BooksRepository

class BookPdfReaderViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    lateinit var bookLink: String
}