package com.example.bookstoreapp.news

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface

class NewsDetailFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_news_details)
//        initToolbar()
//
//        val data: NewsItem = intent.getSerializableExtra("data") as NewsItem
//
//        val content = findViewById<TextView>(R.id.content)!!
//        val title = findViewById<TextView>(R.id.register_title)!!
//        val bookStore = findViewById<TextView>(R.id.bookStore)!!
//        val photo = findViewById<ImageView>(R.id.image)!!
//
//        content.text = data.content
//        title.text = data.title
//        bookStore.text = data.bookStore
//        Glide.with(applicationContext)
//            .load(ApiInterface.photoPath + data.photo)
//            .into(photo)
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.news_toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            setResult(Activity.RESULT_CANCELED)
//            finish()
//        } else {
//            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
