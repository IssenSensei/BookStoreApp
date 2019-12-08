package com.example.bookstoreapp.bookQuotes

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R

class BookQuoteDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_quote_details)

        val data: BookQuotesItem = intent.getSerializableExtra("data") as BookQuotesItem

        val bookTitle = findViewById<TextView>(R.id.bookTitle)!!
        val content = findViewById<TextView>(R.id.content)!!
        val userName = findViewById<TextView>(R.id.userName)!!
        val image = findViewById<ImageView>(R.id.image)!!

        bookTitle.text = data.bookTitle
        content.text = data.content
        userName.text = data.userName
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)
    }
}
