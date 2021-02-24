package com.issen.ebooker.bookQuoteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookQuotesListViewModelFactory(private val bookId: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookQuotesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookQuotesListViewModel(bookId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}