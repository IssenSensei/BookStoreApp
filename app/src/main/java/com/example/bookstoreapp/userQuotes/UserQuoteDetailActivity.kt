package com.example.bookstoreapp.userQuotes

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R

class UserQuoteDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_quote_details)

        val data: UserQuotesItem = intent.getSerializableExtra("data") as UserQuotesItem

        val bookTitle = findViewById<TextView>(R.id.bookTitle)!!
        val image = findViewById<ImageView>(R.id.image)!!
        val quoteContent = findViewById<TextView>(R.id.quoteContent)!!

        quoteContent.text = data.content
        bookTitle.text = data.bookTitle
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)
    }
}
