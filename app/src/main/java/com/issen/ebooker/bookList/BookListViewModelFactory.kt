package com.issen.ebooker.bookList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookListViewModelFactory(private val booksRepository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookListViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}