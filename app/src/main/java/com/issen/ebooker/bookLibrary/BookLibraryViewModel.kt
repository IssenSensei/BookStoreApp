package com.issen.ebooker.bookLibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.data.BooksRepository
import kotlinx.coroutines.launch

class BookLibraryViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val favouriteShelfId = EBookerApplication.favouriteShelfId
    val purchasedShelfId = EBookerApplication.purchasedShelfId
    val toReadShelfId = EBookerApplication.toReadShelfId
    val readingShelfId = EBookerApplication.readingShelfId
    val haveReadShelfId = EBookerApplication.haveReadShelfId
    val reviewedShelfId = EBookerApplication.reviewedShelfId
    val recentlyViewedShelfId = EBookerApplication.recentlyViewedShelfId
    val myEBooksShelfId = EBookerApplication.myEBooksShelfId
    val recommendationsShelfId = EBookerApplication.recommendationsShelfId

    var readingList = booksRepository.getShelfBooks(readingShelfId, auth.currentUser!!.uid)
    var toReadList = booksRepository.getShelfBooks(toReadShelfId, auth.currentUser!!.uid)
    var recentlyViewedList = booksRepository.getShelfBooks(recentlyViewedShelfId, auth.currentUser!!.uid)
    var purchasedList = booksRepository.getShelfBooks(purchasedShelfId, auth.currentUser!!.uid)
    var recommendationsList = booksRepository.getShelfBooks(recommendationsShelfId, auth.currentUser!!.uid)
    var favouriteList = booksRepository.getShelfBooks(favouriteShelfId, auth.currentUser!!.uid)
    var haveReadList = booksRepository.getShelfBooks(haveReadShelfId, auth.currentUser!!.uid)
    var reviewedList = booksRepository.getShelfBooks(reviewedShelfId, auth.currentUser!!.uid)
    var eBooksList = booksRepository.getShelfBooks(myEBooksShelfId, auth.currentUser!!.uid)

    init {
        viewModelScope.launch {
            booksRepository.refreshBooks()
            booksRepository.refreshShelves(auth.currentUser!!.uid)
        }
    }
}