package com.issen.ebooker.bookList

import android.app.Application
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.issen.ebooker.R
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class BookListViewModel(
    private val booksRepository: BooksRepository,
    val shelfId: Int,
    val author: String,
    val publisher: String,
    application: Application
) :
    AndroidViewModel(application) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private lateinit var bookListSource: LiveData<List<Book>>
    val bookList: MediatorLiveData<List<Book>> = MediatorLiveData()

    private val _listTitle = MutableLiveData<String>()
    val listTitle: LiveData<String>
        get() = _listTitle

    init {
        when {
            shelfId != -1 -> {
                getShelfBooks()
                _listTitle.value = application.resources.getStringArray(R.array.shelves)[shelfId]
            }
            author != "" -> {
                getAuthorBooks(author)
                _listTitle.value = author
                viewModelScope.launch {
                    booksRepository.refreshAuthorBooks(author)
                }
            }
            publisher != "" -> {
                getPublisherBooks(publisher)
                _listTitle.value = publisher
                viewModelScope.launch {
                    booksRepository.refreshPublisherBooks(publisher)
                }
            }
        }
        viewModelScope.launch {
            booksRepository.refreshBooks()
        }
    }

    private fun getShelfBooks() {
        clearSource()
        bookListSource = booksRepository.getShelfBooks(shelfId, auth.currentUser!!.uid)
        bookList.addSource(bookListSource) {
            bookList.value = it
        }
    }

    private fun getAuthorBooks(author: String) {
        clearSource()
        bookListSource = booksRepository.getAuthorBooks(author)
        bookList.addSource(bookListSource) {
            bookList.value = it
        }
    }

    private fun getPublisherBooks(publisher: String) {
        clearSource()
        bookListSource = booksRepository.getPublisherBooks(publisher)
        bookList.addSource(bookListSource) {
            bookList.value = it
        }
    }

    private fun clearSource(){
        if(::bookListSource.isInitialized){
            bookList.removeSource(bookListSource)
        }
    }

}