package com.issen.ebooker.bookDetail

import com.issen.ebooker.data.domain.Book

interface BookDetailListener {
    fun onThumbnailClicked(url: String)
    fun onPublisherClicked(publisher: String)
    fun onShowMoreClicked(book: Book)
}