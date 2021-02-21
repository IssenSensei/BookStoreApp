package com.issen.ebooker.bookDetail

interface BookDetailDialogListener {
    fun toggleFavourite()
    fun showQuotes(id: String)
    fun showReviews(id: String)
    fun addReview(id: String)
}