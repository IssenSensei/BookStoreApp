package com.issen.ebooker.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.issen.ebooker.data.conversionExtensions.*
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.data.local.dao.*
import com.issen.ebooker.data.remote.GoogleApiNetwork.googleApi
import com.issen.ebooker.data.remote.models.ResponseBookshelfList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.issen.ebooker.EBookerApplication.Companion.favouriteShelfId
import com.issen.ebooker.data.local.models.DatabaseUserBookItem

class BooksRepository(
    private val bookDao: BookDao,
    private val pdfDao: PdfDao,
    private val epubDao: EpubDao,
    private val imageLinksDao: ImageLinksDao,
    private val userBookDao: UserBookDao
) {

    val books: LiveData<List<Book>> = Transformations.map(bookDao.getBooks()) {
        it.asDomainModel()
    }

    fun getShelfBooks(id: Int, uid: String): LiveData<List<Book>> {
        return bookDao.getShelfBooks(id, uid).map {
            it.asDomainModel()
        }
    }

    suspend fun getUserShelves(): ResponseBookshelfList {
        return googleApi.getUserShelves()
    }

    fun getAuthorBooks(author: String): LiveData<List<Book>> {
        return bookDao.getAuthorBooks(author).map {
            it.asDomainModel()
        }
    }

    fun getPublisherBooks(publisher: String): LiveData<List<Book>> {
        return bookDao.getPublisherBooks(publisher).map {
            it.asDomainModel()
        }
    }

    suspend fun refreshBooks() {
        withContext(Dispatchers.IO) {
            googleApi.getBooks().items?.forEach {
                val pdfId = pdfDao.insert(it.accessInfo.pdf.asDatabasePdf())
                val epubId = epubDao.insert(it.accessInfo.epub.asDatabaseEpub())
                val imageLinksId = if (it.volumeInfo.imageLinks != null) {
                    imageLinksDao.insert(it.volumeInfo.imageLinks.asDatabaseImageLinks())
                } else null
                Log.e("wwwwww", it.toString())
                bookDao.insert(it.asDatabaseBookItem(pdfId.toInt(), epubId.toInt(), imageLinksId?.toInt()))
            }
        }
    }

    suspend fun refreshShelves(uid: String) {
        withContext(Dispatchers.IO) {
            googleApi.getUserShelves().items?.forEach { bookshelf ->
                if (bookshelf.id < 9) {
                    val bookList = mutableListOf<DatabaseUserBookItem>()
                    googleApi.getShelfBooks(bookshelf.id).items?.forEach {
                        Log.e("qqqqqq", it.toString())
                        bookList.add(DatabaseUserBookItem(it.id, bookshelf.id, uid))
                        val pdfId = pdfDao.insert(it.accessInfo.pdf.asDatabasePdf())
                        val epubId = epubDao.insert(it.accessInfo.epub.asDatabaseEpub())
                        val imageLinksId = if (it.volumeInfo.imageLinks != null) {
                            imageLinksDao.insert(it.volumeInfo.imageLinks.asDatabaseImageLinks())
                        } else null
                        bookDao.insert(it.asDatabaseBookItem(pdfId.toInt(), epubId.toInt(), imageLinksId?.toInt()))
                    }
                    userBookDao.refreshShelfBooks(bookList)
                }
            }
        }
    }

    suspend fun refreshAuthorBooks(author: String) {
        googleApi.getQueriedBooks("inauthor:$author").items?.forEach {
            val pdfId = pdfDao.insert(it.accessInfo.pdf.asDatabasePdf())
            val epubId = epubDao.insert(it.accessInfo.epub.asDatabaseEpub())
            val imageLinksId = if (it.volumeInfo.imageLinks != null) {
                imageLinksDao.insert(it.volumeInfo.imageLinks.asDatabaseImageLinks())
            } else null
            bookDao.insert(it.asDatabaseBookItem(pdfId.toInt(), epubId.toInt(), imageLinksId?.toInt()))
        }
    }

    suspend fun refreshPublisherBooks(publisher: String) {
        withContext(Dispatchers.IO) {
            googleApi.getQueriedBooks("inpublisher$publisher").items?.forEach {
                val pdfId = pdfDao.insert(it.accessInfo.pdf.asDatabasePdf())
                val epubId = epubDao.insert(it.accessInfo.epub.asDatabaseEpub())
                val imageLinksId = if (it.volumeInfo.imageLinks != null) {
                    imageLinksDao.insert(it.volumeInfo.imageLinks.asDatabaseImageLinks())
                } else null
                bookDao.insert(it.asDatabaseBookItem(pdfId.toInt(), epubId.toInt(), imageLinksId?.toInt()))
            }
        }
    }

    suspend fun checkIsFavourite(bookId: String, uid: String): Boolean {
        return userBookDao.checkIsFavourite(bookId, favouriteShelfId, uid)
    }

    suspend fun deleteFromFavourites(bookId: String, uid: String) {
        userBookDao.removeFromFavourites(DatabaseUserBookItem(bookId, favouriteShelfId, uid))
        googleApi.removeFromUserShelf(favouriteShelfId, bookId)
    }

    suspend fun addToFavourites(bookId: String, uid: String) {
        userBookDao.addToFavourites(DatabaseUserBookItem(bookId, favouriteShelfId, uid))
        googleApi.addToUserShelf(favouriteShelfId, bookId)
    }

    suspend fun getBookTitle(bookId: String): String {
        return bookDao.getBookTitle(bookId) ?: ""
    }

    suspend fun getBookRating(bookId: String): Float {
        //todo get book average rating
        return 2.5f
    }
}