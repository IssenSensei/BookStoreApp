package com.example.bookstoreapp.utils

import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.bookQuotes.BookQuotesRecyclerViewAdapter
import com.example.bookstoreapp.books.BooksRecyclerViewAdapter
import com.example.bookstoreapp.news.NewsRecyclerViewAdapter
import com.example.bookstoreapp.userQuotes.UserQuotesRecyclerViewAdapter


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

