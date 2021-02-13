package com.issen.ebooker.bookList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.ItemBookListBinding
import com.issen.ebooker.utils.BookListDiffCallback

class BookListRecyclerViewAdapter : ListAdapter<Book, BookListRecyclerViewAdapter.ViewHolder>(BookListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ViewHolder(private val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book) {
            binding.book = item
            binding.executePendingBindings()
        }
    }
}