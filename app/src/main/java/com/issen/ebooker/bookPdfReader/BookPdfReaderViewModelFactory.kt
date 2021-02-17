package com.issen.ebooker.bookPdfReader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BookPdfReaderViewModelFactory(private val booksRepository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookPdfReaderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookPdfReaderViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}