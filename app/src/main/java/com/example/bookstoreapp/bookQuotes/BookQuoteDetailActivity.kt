package com.example.bookstoreapp.bookQuotes

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import kotlinx.android.synthetic.main.activity_book_quote_details.*

class BookQuoteDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_quote_details)

        val data: BookQuotesItem = intent.getSerializableExtra("data") as BookQuotesItem


        bookTitle.text = data.bookTitle
        content.text = data.content
        userName.text = data.userName
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)
    }
}
