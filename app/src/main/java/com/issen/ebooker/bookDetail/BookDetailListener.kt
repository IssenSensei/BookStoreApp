package com.issen.ebooker.bookDetail

interface BookDetailListener {
    fun onThumbnailClicked(url: String)
    fun onPublisherClicked(publisher: String)
    fun onShowMoreClicked()
}