package com.issen.ebooker.bookReviewsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.issen.ebooker.data.BooksRepository
import kotlinx.coroutines.launch


class BookReviewsListViewModel(private val booksRepository: BooksRepository, val bookId: String) : ViewModel() {

    private val _rating = MutableLiveData<Float>()
    val rating: LiveData<Float>
        get() = _rating

    private val _bookTitle = MutableLiveData<String>()
    val bookTitle: LiveData<String>
        get() = _bookTitle

    val database = Firebase.database
    var databaseRef = database.getReference("reviews")

    init {
        databaseRef = databaseRef.child(bookId)
        viewModelScope.launch {
            _bookTitle.value = booksRepository.getBookTitle(bookId)
            _rating.value = booksRepository.getBookRating(bookId)
        }
    }
}