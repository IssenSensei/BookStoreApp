package com.issen.ebooker.bookLibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookLibraryViewModelFactory(private val booksRepository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookLibraryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookLibraryViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}