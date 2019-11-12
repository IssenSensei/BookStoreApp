package com.example.bookstoreapp.userQuotes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R

class UserQuotesRecyclerViewAdapter(private val userQuotesMap: HashMap<String, UserQuotesItem>)
                                    : RecyclerView.Adapter<UserQuotesRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount() = userQuotesMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = userQuotesMap.toList()
        val second = data[position].second

        holder.bookTitle.text = second.bookTitle
        holder.content.text = second.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_user_quotes, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: UserQuotesItem? = null

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val content = mView.findViewById<TextView>(R.id.quoteContent)!!
    }
}
