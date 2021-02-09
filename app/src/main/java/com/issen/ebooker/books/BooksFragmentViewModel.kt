package com.issen.ebooker.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.coroutines.launch

class BooksFragmentViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    val bookList: LiveData<List<Book>> = booksRepository.books

    init {
        viewModelScope.launch {
            booksRepository.refreshBooks()
        }
    }
}