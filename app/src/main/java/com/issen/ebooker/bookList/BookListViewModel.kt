package com.issen.ebooker.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.coroutines.launch

class BookListViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var shelfId = -1
    var author = ""
    var publisher = ""

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>>
        get() {
           return _bookList
        }



    init {
        viewModelScope.launch {
            booksRepository.refreshBooks()
        }
    }

    fun getShelfBooks(id: Int) {
        viewModelScope.launch {
            _bookList.value = booksRepository.getShelfBooks(id)
        }
    }

    fun getAuthorBooks(author: String) {
        viewModelScope.launch {
            _bookList.value = booksRepository.getAuthorBooks(author)
        }
    }

    fun getPublisherBooks(publisher: String) {
        viewModelScope.launch {
            _bookList.value = booksRepository.getPublisherBooks(publisher)
        }
    }

}