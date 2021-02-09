package com.issen.ebooker.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.issen.ebooker.data.BooksRepository
import java.lang.IllegalArgumentException

class BooksFragmentViewModelFactory(private val booksRepository: BooksRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksFragmentViewModel(booksRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}