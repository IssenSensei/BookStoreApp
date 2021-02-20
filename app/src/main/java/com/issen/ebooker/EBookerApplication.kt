package com.issen.ebooker

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.local.EBookerDatabase

class EBookerApplication : Application() {

    private val database by lazy { EBookerDatabase.getDatabase(this) }
    val booksRepository by lazy {
        BooksRepository(
            database.bookDao,
            database.pdfDao,
            database.epubDao,
            database.imageLinksDao,
            database.userBookDao,
            database.reviewDao
        )
    }

    companion object {
        const val favouriteShelfId = 0
        const val purchasedShelfId = 1
        const val toReadShelfId = 2
        const val readingShelfId = 3
        const val haveReadShelfId = 4
        const val reviewedShelfId = 5
        const val recentlyViewedShelfId = 6
        const val myEBooksShelfId = 7
        const val recommendationsShelfId = 8
    }
}