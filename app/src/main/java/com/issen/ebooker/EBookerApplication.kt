package com.issen.ebooker

import android.app.Application
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.local.EBookerDatabase

class EBookerApplication : Application() {

    val database by lazy { EBookerDatabase.getDatabase(this) }
    val booksRepository by lazy { BooksRepository(database.bookDao, database.pdfDao, database.epubDao, database.imageLinksDao) }
}