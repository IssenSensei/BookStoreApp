package com.issen.ebooker.bookLibrary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.ItemBookLibraryBinding
import com.issen.ebooker.common.BookListDiffCallback

class BookLibraryRecyclerViewAdapter(private val libraryListener: LibraryListener) : ListAdapter<Book, BookLibraryRecyclerViewAdapter.ViewHolder>(BookListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookLibraryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, libraryListener)
    }

    class ViewHolder(private val binding: ItemBookLibraryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Book, libraryListener: LibraryListener) {
            binding.book = item
            binding.listener = libraryListener
            binding.executePendingBindings()
        }
    }
}

