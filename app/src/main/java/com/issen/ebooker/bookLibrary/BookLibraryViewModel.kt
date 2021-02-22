package com.issen.ebooker.bookLibrary

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _areReadingNowObserved = MutableLiveData<Boolean>()
    val areReadingNowObserved: LiveData<Boolean>
        get() = _areReadingNowObserved

    private val _areToReadObserved = MutableLiveData<Boolean>()
    val areToReadObserved: LiveData<Boolean>
        get() = _areToReadObserved

    private val _areRecentlyViewedObserved = MutableLiveData<Boolean>()
    val areRecentlyViewedObserved: LiveData<Boolean>
        get() = _areRecentlyViewedObserved

    private val _arePurchasedObserved = MutableLiveData<Boolean>()
    val arePurchasedObserved: LiveData<Boolean>
        get() = _arePurchasedObserved

    private val _areRecommendationsObserved = MutableLiveData<Boolean>()
    val areRecommendationsObserved: LiveData<Boolean>
        get() = _areRecommendationsObserved

    private val _areFavouritesObserved = MutableLiveData<Boolean>()
    val areFavouritesObserved: LiveData<Boolean>
        get() = _areFavouritesObserved

    private val _areHaveReadObserved = MutableLiveData<Boolean>()
    val areHaveReadObserved: LiveData<Boolean>
        get() = _areHaveReadObserved

    private val _areReviewedObserved = MutableLiveData<Boolean>()
    val areReviewedObserved: LiveData<Boolean>
        get() = _areReviewedObserved

    private val _areEBooksObserved = MutableLiveData<Boolean>()
    val areEBooksObserved: LiveData<Boolean>
        get() = _areEBooksObserved

    init {
        viewModelScope.launch {
            booksRepository.refreshBooks()
            booksRepository.refreshShelves(auth.currentUser!!.uid)
        }
    }

    fun checkVisibilitySettings(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.getBoolean("reading_now", true) != _areReadingNowObserved.value) {
            _areReadingNowObserved.value = sharedPreferences.getBoolean("reading_now", true)
        }
        if (sharedPreferences.getBoolean("to_read", true) != _areToReadObserved.value) {
            _areToReadObserved.value = sharedPreferences.getBoolean("to_read", true)
        }
        if (sharedPreferences.getBoolean("recently_viewed", true) != _areRecentlyViewedObserved.value) {
            _areRecentlyViewedObserved.value = sharedPreferences.getBoolean("recently_viewed", true)
        }
        if (sharedPreferences.getBoolean("purchased", true) != _arePurchasedObserved.value) {
            _arePurchasedObserved.value = sharedPreferences.getBoolean("purchased", true)
        }
        if (sharedPreferences.getBoolean("recommendations", true) != _areRecommendationsObserved.value) {
            _areRecommendationsObserved.value = sharedPreferences.getBoolean("recommendations", true)
        }
        if (sharedPreferences.getBoolean("favourites", true) != _areFavouritesObserved.value) {
            _areFavouritesObserved.value = sharedPreferences.getBoolean("favourites", true)
        }
        if (sharedPreferences.getBoolean("have_read", true) != _areHaveReadObserved.value) {
            _areHaveReadObserved.value = sharedPreferences.getBoolean("have_read", true)
        }
        if (sharedPreferences.getBoolean("reviewed", true) != _areReviewedObserved.value) {
            _areReviewedObserved.value = sharedPreferences.getBoolean("reviewed", true)
        }
        if (sharedPreferences.getBoolean("ebooks", true) != _areEBooksObserved.value) {
            _areEBooksObserved.value = sharedPreferences.getBoolean("ebooks", true)
        }
    }
}