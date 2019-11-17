package com.example.bookstoreapp.bookQuotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.ItemAnimation
import com.example.bookstoreapp.utils.Tools
import com.example.bookstoreapp.utils.ViewAnimation

class BookQuotesRecyclerViewAdapter(private val bookQuotesMap: MutableList<BookQuotesItem>)
                                    : RecyclerView.Adapter<BookQuotesRecyclerViewAdapter.ViewHolder>() {
    private val animation_type = ItemAnimation.FADE_IN

    override fun getItemCount() = bookQuotesMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = bookQuotesMap.toList()
        val second = data[position]

        holder.bookTitle.text = second.bookTitle
        holder.quoteContent.text = second.content
        holder.userName.text = second.userName

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
        setAnimation(holder.itemView, position)


    }

    private var lastPosition = -1
    private var on_attach = true

    private fun setAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, if (on_attach) position else -1, animation_type)
            lastPosition = position
        }
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_book_quotes, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BookQuotesItem? = null

        val lytExpand = mView.findViewById(R.id.lyt_expand) as View
        val expand = mView.findViewById(R.id.bt_expand) as ImageButton

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val quoteContent = mView.findViewById<TextView>(R.id.quoteContent)!!
        val userName = mView.findViewById<TextView>(R.id.userName)!!
    }
}
