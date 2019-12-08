package com.example.bookstoreapp.bookQuotes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.Tools
import com.example.bookstoreapp.utils.ViewAnimation

class BookQuotesRecyclerViewAdapter(
    private val bookQuotesMap: MutableList<BookQuotesItem>,
    private val context: Context
) : RecyclerView.Adapter<BookQuotesRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = bookQuotesMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = bookQuotesMap.toList()
        val second = data[position]

        holder.bookTitle.text = second.bookTitle
        holder.quoteContent.text = second.content

        holder.expand.setOnClickListener { v ->
            val show = toggleLayoutExpand(!second.expanded, v, holder.lytExpand)
            second.expanded = show
        }

        if (second.expanded) {
            holder.lytExpand.visibility = View.VISIBLE
        } else {
            holder.lytExpand.visibility = View.GONE
        }
        Tools.toggleArrow(second.expanded, holder.expand, false)

        holder.listener.setOnClickListener {
            seeDetails(context, second)
        }
    }


    private fun seeDetails(context: Context, data: BookQuotesItem) {

        val intent = Intent(context, BookQuoteDetailActivity::class.java)
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
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_quotes_item, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        val lytExpand = mView.findViewById(R.id.lyt_expand) as View
        val expand = mView.findViewById(R.id.bt_expand) as ImageButton

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val quoteContent = mView.findViewById<TextView>(R.id.quoteContent)!!
        val listener: LinearLayout = mView.findViewById(R.id.book_quotes_listener)

    }
}
