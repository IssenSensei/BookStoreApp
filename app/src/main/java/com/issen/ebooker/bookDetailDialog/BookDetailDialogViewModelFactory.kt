package com.issen.ebooker.bookDetailDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import java.lang.IllegalArgumentException

class BookDetailDialogViewModelFactory(private val booksRepository: BooksRepository, private val book: Book) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookDetailDialogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookDetailDialogViewModel(booksRepository, book) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}