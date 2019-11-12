package com.example.bookstoreapp.bookQuotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R

class BookQuotesRecyclerViewAdapter(private val bookQuotesMap: HashMap<String, BookQuotesItem>)
                                    : RecyclerView.Adapter<BookQuotesRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount() = bookQuotesMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = bookQuotesMap.toList()
        val second = data[position].second

        holder.bookTitle.text = second.bookTitle
        holder.quoteContent.text = second.content
        holder.userName.text = second.userName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_book_quotes, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BookQuotesItem? = null

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val quoteContent = mView.findViewById<TextView>(R.id.quoteContent)!!
        val userName = mView.findViewById<TextView>(R.id.userName)!!
    }
}
