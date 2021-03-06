package com.issen.ebooker.bookReviewsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookReviewsListViewModelFactory(private val booksRepository: BooksRepository, private val bookId: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookReviewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookReviewsListViewModel(booksRepository, bookId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}