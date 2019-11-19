package com.example.bookstoreapp.news

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

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val data: NewsItem = intent.getSerializableExtra("data") as NewsItem

        val content = findViewById<TextView>(R.id.content)!!
        val title = findViewById<TextView>(R.id.title)!!
        val login = findViewById<TextView>(R.id.login)!!
        val picture = findViewById<ImageView>(R.id.image)!!

        content.text = data.content
        title.text = data.title
        login.text = data.login
        Glide.with(applicationContext)
            .load(data.picture)
            .into(picture)
    }
}
