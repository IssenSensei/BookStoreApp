package com.example.bookstoreapp.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.ItemAnimation
import com.example.bookstoreapp.utils.Tools
import com.example.bookstoreapp.utils.ViewAnimation

class NewsRecyclerViewAdapter(private val newsMap: MutableList<NewsItem>,
                              private val context: Context)
                                    : RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = newsMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = newsMap.toList()
        val second = data[position]

        holder.login.text = second.login
        holder.newsTitle.text = second.title
        holder.newsContent.text = second.content
        Glide.with(holder.mView.context)
            .load(second.picture)
            .into(holder.image)

        holder.expand.setOnClickListener { v ->
            val show = toggleLayoutExpand(!second.expanded, v, holder.lytExpand)
            second.expanded = show
        }

        if (second.expanded) {
            holder.lytExpand.setVisibility(View.VISIBLE)
        } else {
            holder.lytExpand.setVisibility(View.GONE)
        }
        Tools.toggleArrow(second.expanded, holder.expand, false)

        holder.linearListener.setOnClickListener {
            seeDetails(context, second)
        }

    }

    private fun seeDetails(context: Context, data: NewsItem){

        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.apply {
            putExtra("data", data)
        }
        context.startActivity(intent)
    }

    private fun toggleLayoutExpand(show: Boolean, view: View, lyt_expand: View): Boolean {
        Tools.toggleArrow(show, view)
        if (show) {
            ViewAnimation.expand(lyt_expand)
        } else {
            ViewAnimation.collapse(lyt_expand)
        }
        return show
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_news, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: NewsItem? = null

        val lytExpand = mView.findViewById(R.id.lyt_expand) as View
        val expand = mView.findViewById(R.id.bt_expand) as ImageButton

        val login = mView.findViewById<TextView>(R.id.login)!!
        val newsTitle = mView.findViewById<TextView>(R.id.newsTitle)!!
        val newsContent = mView.findViewById<TextView>(R.id.newsContent)!!
        val image = mView.findViewById<ImageView>(R.id.imgvw)!!

        val linearListener = mView.findViewById<LinearLayout>(R.id.linear_listener)

    }
}
