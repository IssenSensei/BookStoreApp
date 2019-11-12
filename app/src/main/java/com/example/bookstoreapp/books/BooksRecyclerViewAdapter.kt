package com.example.bookstoreapp.books

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R

class BooksRecyclerViewAdapter(private val booksMap: HashMap<String, BooksItem>)
    : RecyclerView.Adapter<BooksRecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount() = booksMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = booksMap.toList()
        val second = data[position].second
        holder.bookTitle.text = second.title
        holder.bookDescription.text = second.description
        //holder.image.setImageResource(second.picture)
        Glide.with(holder.mView.context)
            .load(second.picture)
            .into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_books, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BooksItem? = null

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val bookDescription = mView.findViewById<TextView>(R.id.bookDescription)!!
        val image = mView.findViewById<ImageView>(R.id.imgvw)!!
    }
}
