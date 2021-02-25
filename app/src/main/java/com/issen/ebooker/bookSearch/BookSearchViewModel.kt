package com.issen.ebooker.bookSearch

import androidx.lifecycle.*
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.coroutines.launch

class BookSearchViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var bookList: MediatorLiveData<List<Book>> = MediatorLiveData()
    var source: LiveData<List<Book>>

    init {
        source = booksRepository.books
        setBookSource()
    }

    fun updateFilter(query: String) {
        bookList.removeSource(source)
        source = booksRepository.getQueriedBooks(query)
        setBookSource()
        if (query.isNotEmpty()) {
            viewModelScope.launch {
                booksRepository.refreshFilteredBooks(query)
            }
        }
    }

    private fun setBookSource() {
        bookList.addSource(source) {
            bookList.value = it
        }
    }
}