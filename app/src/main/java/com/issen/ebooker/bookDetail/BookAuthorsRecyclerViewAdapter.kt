package com.issen.ebooker.bookDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.databinding.ItemAuthorSimpleBinding

class BookAuthorsRecyclerViewAdapter(private val authors: List<String>, private val authorListener: AuthorListener) :
    RecyclerView.Adapter<BookAuthorsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemAuthorSimpleBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val author = authors[position]
        holder.bind(author, authorListener)
    }

    override fun getItemCount(): Int = authors.size

    inner class ViewHolder(private val binding: ItemAuthorSimpleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(author: String, authorListener: AuthorListener) {
            binding.author = author
            binding.listener = authorListener
            binding.executePendingBindings()
        }
    }
}