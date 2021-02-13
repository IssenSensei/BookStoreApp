package com.issen.ebooker.utils

import androidx.recyclerview.widget.DiffUtil
import com.issen.ebooker.data.domain.Book

class BookListDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.bookId == newItem.bookId
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}