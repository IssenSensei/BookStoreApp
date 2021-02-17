package com.issen.ebooker.bookQuoteList

import androidx.lifecycle.ViewModel
import com.issen.ebooker.data.BooksRepository

class BookQuoteListViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    lateinit var bookId: String
}