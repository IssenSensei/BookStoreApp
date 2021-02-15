package com.issen.ebooker.bookDetail

import androidx.lifecycle.ViewModel
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Book

class BookDetailViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    lateinit var book: Book

}