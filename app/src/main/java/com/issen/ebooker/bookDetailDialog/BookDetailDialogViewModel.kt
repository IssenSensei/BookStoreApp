package com.issen.ebooker.bookDetailDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.coroutines.launch

class BookDetailDialogViewModel(private val booksRepository: BooksRepository, val book: Book) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    init {
        viewModelScope.launch {
            _isFavourite.value = booksRepository.checkIsFavourite(book.bookId, auth.currentUser!!.uid)
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            if (_isFavourite.value!!) {
                booksRepository.deleteFromFavourites(book.bookId, auth.currentUser!!.uid)
            } else {
                booksRepository.addToFavourites(book.bookId, auth.currentUser!!.uid)
            }
        }
    }
}