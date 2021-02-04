package com.issen.ebooker.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.R

class CommentsRecyclerViewAdapter(
    private var commentMap: MutableList<CommentItem>
)
    : RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder>(){

    override fun getItemCount() = commentMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = commentMap.toList()
        val second = data[position]
        holder.username.text = second.username
        holder.rating.text = second.rating.toString()
        holder.content.text = second.content

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val username = mView.findViewById(R.id.comment_username) as TextView
        val rating = mView.findViewById(R.id.comment_rating) as TextView
        val content = mView.findViewById(R.id.comment_content) as TextView


    }
}
