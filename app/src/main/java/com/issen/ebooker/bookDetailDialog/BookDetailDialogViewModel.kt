package com.issen.ebooker.bookDetailDialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book
import kotlinx.coroutines.launch

class BookDetailDialogViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    lateinit var book: Book

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean>
        get() = _isFavourite

    init {
        _isFavourite.value = false
    }

    fun isFavourite() {
        viewModelScope.launch {
            _isFavourite.value = booksRepository.checkIsFavourite(book.bookId)
        }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            if(_isFavourite.value!!){
                booksRepository.deleteFromFavourites(book.bookId)
            } else{
                booksRepository.addToFavourites(book.bookId)
            }
        }
    }
}