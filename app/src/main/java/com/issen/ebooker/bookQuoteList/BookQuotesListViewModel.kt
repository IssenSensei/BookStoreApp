package com.issen.ebooker.bookQuoteList

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.issen.ebooker.data.BooksRepository

class BookQuotesListViewModel(private val booksRepository: BooksRepository, val bookId: String) : ViewModel() {
    var databaseRef = Firebase.database.getReference("quotes")

    init {
        databaseRef = databaseRef.child(bookId)
    }
}