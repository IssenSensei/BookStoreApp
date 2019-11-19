package com.example.bookstoreapp.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import kotlinx.android.synthetic.main.activity_book_details.*

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val data: BooksItem = intent.getSerializableExtra("data") as BooksItem

        val bookTitle = findViewById<TextView>(R.id.bookTitle)!!
        val bookDescription = findViewById<TextView>(R.id.bookDescription)!!
        val year = findViewById<TextView>(R.id.yearView)!!
        val publisher = findViewById<TextView>(R.id.publisherView)!!
        val image = findViewById<ImageView>(R.id.image)!!
        val author = findViewById<TextView>(R.id.authorView)!!

        author.text = data.author
        publisher.text = data.publisher
        year.text = data.year
        bookDescription.text = data.description
        bookTitle.text = data.title
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)
    }
}
