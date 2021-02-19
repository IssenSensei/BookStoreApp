package com.issen.ebooker.bookQuoteList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.issen.ebooker.data.local.models.DatabaseQuotationItem
import com.issen.ebooker.databinding.ItemBookQuotationBinding

class BookQuotesFirebaseRecyclerAdapter(options: FirebaseRecyclerOptions<DatabaseQuotationItem>) :
    FirebaseRecyclerAdapter<DatabaseQuotationItem, BookQuotesFirebaseRecyclerAdapter.ViewHolder>(
        options
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBookQuotationBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        model: DatabaseQuotationItem
    ) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemBookQuotationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DatabaseQuotationItem) {
            binding.quotation = item
            binding.executePendingBindings()
        }
    }
}