package com.example.bookstoreapp.bookQuotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_book_quote_details.*

class BookQuoteDetailActivity : AppCompatActivity() {

    private lateinit var currentTheme: String
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
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

    override fun onResume() {
        super.onResume()
        val theme = sharedPreference.getValueString("current_theme")
        if(currentTheme != theme)
            recreate()
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            "THEME_DARKISH" -> setTheme(R.style.Theme_App_Darkish)
            "THEME_PURPLISH" -> setTheme(R.style.Theme_App_Purplish)
            "THEME_GREENISH" -> setTheme(R.style.Theme_App_Greenish)
            "THEME_FULLWHITE" -> setTheme(R.style.Theme_App_FullWhite)
            else -> setTheme(R.style.Theme_App_Whitish)
        }
    }
}
