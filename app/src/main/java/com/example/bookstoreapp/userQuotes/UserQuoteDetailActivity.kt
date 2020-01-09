package com.example.bookstoreapp.userQuotes

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface

class UserQuoteDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_quote_details)
        initToolbar()

        val data: UserQuotesItem = intent.getSerializableExtra("data") as UserQuotesItem

        val bookTitle = findViewById<TextView>(R.id.bookTitle)!!
        val image = findViewById<ImageView>(R.id.image)!!
        val quoteContent = findViewById<TextView>(R.id.quoteContent)!!

        quoteContent.text = data.content
        bookTitle.text = data.bookTitle
        Glide.with(applicationContext)
            .load(ApiInterface.photoPath + data.picture)
            .into(image)
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.user_quote_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED)
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}
