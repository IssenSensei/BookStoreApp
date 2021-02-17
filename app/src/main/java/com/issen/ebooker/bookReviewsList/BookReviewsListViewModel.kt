package com.issen.ebooker.bookReviewsList

import androidx.lifecycle.ViewModel
import com.issen.ebooker.data.BooksRepository

class BookReviewsListViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    lateinit var bookId: String
}