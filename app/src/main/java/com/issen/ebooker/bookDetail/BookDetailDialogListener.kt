package com.issen.ebooker.bookDetail

interface BookDetailDialogListener {
    fun readAsEPub(link: String)
    fun readAsPdf(link: String)
    fun toggleFavourite()
    fun showQuotes(id: String)
    fun showReviews(id: String)
    fun addReview(id: String)
}