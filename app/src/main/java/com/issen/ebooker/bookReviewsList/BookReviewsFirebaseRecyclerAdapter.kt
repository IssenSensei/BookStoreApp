package com.issen.ebooker.bookReviewsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.issen.ebooker.data.domain.Review
import com.issen.ebooker.databinding.ItemBookReviewBinding


class BookReviewsFirebaseRecyclerAdapter(options: FirebaseRecyclerOptions<Review>) :
    FirebaseRecyclerAdapter<Review, BookReviewsFirebaseRecyclerAdapter.ViewHolder>(
        options
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookReviewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        model: Review
    ) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemBookReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.review = item
            binding.executePendingBindings()
        }
    }
}