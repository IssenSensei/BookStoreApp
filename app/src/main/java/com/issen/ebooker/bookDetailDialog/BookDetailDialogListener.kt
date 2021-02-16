package com.issen.ebooker.bookDetailDialog

interface BookDetailDialogListener {
    fun readAsEPub(link: String)
    fun readAsPdf(link: String)
    fun toggleFavourite()
    fun showQuotes(id: String)
    fun showReviews(id: String)
}