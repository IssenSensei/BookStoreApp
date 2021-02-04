package com.example.bookstoreapp.bookQuotes

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import kotlinx.android.synthetic.main.activity_book_quote_details.*

class BookQuoteDetailFragment : Fragment() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_book_quote_details)
//        initToolbar()
//
//        val data: BookQuotesItem = intent.getSerializableExtra("data") as BookQuotesItem
//
//
//        bookTitle.text = data.bookTitle
//        content.text = data.content
//        Glide.with(applicationContext)
//            .load(ApiInterface.photoPath + data.picture)
//            .into(image)
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.book_quote_toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Powrót"
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
