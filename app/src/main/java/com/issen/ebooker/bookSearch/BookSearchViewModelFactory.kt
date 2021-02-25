package com.issen.ebooker.bookSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookSearchViewModelFactory(private val booksRepository: BooksRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookSearchViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}