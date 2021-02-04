package com.issen.ebooker.utils

import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.bookQuotes.BookQuotesRecyclerViewAdapter
import com.issen.ebooker.books.BooksRecyclerViewAdapter
import com.issen.ebooker.news.NewsRecyclerViewAdapter
import com.issen.ebooker.userQuotes.UserQuotesRecyclerViewAdapter


fun RecyclerView.getUserQuotesAdapter(): UserQuotesRecyclerViewAdapter {
    return adapter as UserQuotesRecyclerViewAdapter
}

fun RecyclerView.getBookQuotesAdapter(): BookQuotesRecyclerViewAdapter {
    return adapter as BookQuotesRecyclerViewAdapter
}

fun RecyclerView.getBooksAdapter(): BooksRecyclerViewAdapter {
    return adapter as BooksRecyclerViewAdapter
}

fun RecyclerView.getNewsAdapter(): NewsRecyclerViewAdapter {
    return adapter as NewsRecyclerViewAdapter
}

