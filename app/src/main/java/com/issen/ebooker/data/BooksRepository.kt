package com.issen.ebooker.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.issen.ebooker.BaseActivity.Companion.token
import com.issen.ebooker.BuildConfig
import com.issen.ebooker.data.conversionExtensions.*
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.local.dao.BookDao
import com.issen.ebooker.data.local.dao.EpubDao
import com.issen.ebooker.data.local.dao.ImageLinksDao
import com.issen.ebooker.data.local.dao.PdfDao
import com.issen.ebooker.data.remote.GoogleApiNetwork.googleApi
import com.issen.ebooker.data.remote.models.ResponseBookshelfList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(
    private val bookDao: BookDao,
    private val pdfDao: PdfDao,
    private val epubDao: EpubDao,
    private val imageLinksDao: ImageLinksDao
) {

    val books: LiveData<List<Book>> = Transformations.map(bookDao.getBooks()) {
        it.asDomainModel()
    }

    suspend fun getShelfBooks(id: Int): List<Book> {
        return googleApi.getShelfBooks("Bearer $token", id).asDomainModel()
    }

    suspend fun getUserShelves(): ResponseBookshelfList {
        return googleApi.getUserShelves("Bearer $token")
    }

    suspend fun getAuthorBooks(author: String): List<Book> {
        return googleApi.getQueriedBooks("inauthor:$author", "Bearer $token").asDomainModel()
    }

    suspend fun getPublisherBooks(publisher: String): List<Book> {
        return googleApi.getQueriedBooks("inpublisher$publisher", "Bearer $token").asDomainModel()
    }

    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            googleApi.getBooks().items?.forEach {
                val pdfId = pdfDao.insert(it.accessInfo.pdf.asDatabasePdf())
                val epubId = epubDao.insert(it.accessInfo.epub.asDatabaseEpub())
                val imageLinksId = if (it.volumeInfo.imageLinks != null) {
                    imageLinksDao.insert(it.volumeInfo.imageLinks.asDatabaseImageLinks())
                } else null
                bookDao.insert(it.asDatabaseBookItem(pdfId.toInt(), epubId.toInt(), imageLinksId?.toInt()))
            }
        }
    }
}