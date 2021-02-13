package com.issen.ebooker.utils

import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.bookQuotes.BookQuotesRecyclerViewAdapter
import com.issen.ebooker.bookList.BookListRecyclerViewAdapter
import com.issen.ebooker.news.NewsRecyclerViewAdapter
import com.issen.ebooker.userQuotes.UserQuotesRecyclerViewAdapter


fun RecyclerView.getUserQuotesAdapter(): UserQuotesRecyclerViewAdapter {
    return adapter as UserQuotesRecyclerViewAdapter
}

fun RecyclerView.getBookQuotesAdapter(): BookQuotesRecyclerViewAdapter {
    return adapter as BookQuotesRecyclerViewAdapter
}

fun RecyclerView.getBooksAdapter(): BookListRecyclerViewAdapter {
    return adapter as BookListRecyclerViewAdapter
}

fun RecyclerView.getNewsAdapter(): NewsRecyclerViewAdapter {
    return adapter as NewsRecyclerViewAdapter
}

