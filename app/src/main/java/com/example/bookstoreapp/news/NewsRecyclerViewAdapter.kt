package com.example.bookstoreapp.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface

class NewsRecyclerViewAdapter(private var newsMap: MutableList<NewsItem>,
                              private val context: Context)
                                    : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = newsMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = newsMap.toList()
        val second = data[position]

        holder.bookStore.text = second.bookStore
        holder.newsTitle.text = second.title
        holder.newsContent.text = second.content
        Glide.with(holder.mView.context)
            .load(ApiInterface.photoPath + second.photo)
            .into(holder.image)

        holder.listener.setOnClickListener {
            seeDetails(context, second)
        }

    }

    private fun seeDetails(context: Context, data: NewsItem){

        val intent = Intent(context, NewsDetailFragment::class.java)
        intent.apply {
            putExtra("data", data)
        }
        context.startActivity(intent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    fun updateList(newNewsMap: MutableList<NewsItem>) {
        newsMap = newNewsMap
        notifyDataSetChanged()
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val bookStore = mView.findViewById<TextView>(R.id.bookStore)!!
        val newsTitle = mView.findViewById<TextView>(R.id.newsTitle)!!
        val newsContent = mView.findViewById<TextView>(R.id.newsContent)!!
        val image = mView.findViewById<ImageView>(R.id.imgvw)!!

        val listener = mView.findViewById<CardView>(R.id.news_listener)

    }
}
