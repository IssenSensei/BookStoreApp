package com.example.bookstoreapp.userQuotes

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
import com.example.bookstoreapp.utils.ItemAnimation
import com.example.bookstoreapp.utils.Tools
import com.example.bookstoreapp.utils.ViewAnimation

class UserQuotesRecyclerViewAdapter(
    private val userQuotesMap: MutableList<UserQuotesItem>,
    private val context: Context
)
    : RecyclerView.Adapter<UserQuotesRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = userQuotesMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = userQuotesMap.toList()
        val second = data[position]

        holder.bookTitle.text = second.bookTitle
        holder.content.text = second.content

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

    private fun seeDetails(context: Context, data: UserQuotesItem){

        val intent = Intent(context, UserQuoteDetailActivity::class.java)
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_user_quotes, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: UserQuotesItem? = null

        val lytExpand = mView.findViewById(R.id.lyt_expand) as View
        val expand = mView.findViewById(R.id.bt_expand) as ImageButton

        val bookTitle = mView.findViewById<TextView>(R.id.bookTitle)!!
        val content = mView.findViewById<TextView>(R.id.quoteContent)!!

        val linearListener = mView.findViewById<LinearLayout>(R.id.linear_listener)

    }
}
