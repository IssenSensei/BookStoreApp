package com.issen.ebooker.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.ItemBookListBinding

class BookListRecyclerViewAdapter(private val listener: BookListListener) : ListAdapter<Book, BookListRecyclerViewAdapter.ViewHolder>(BookListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, listener)
    }

    class ViewHolder(private val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book, listener: BookListListener) {
            binding.book = item
            binding.listener = listener
            binding.executePendingBindings()
        }
    }
}