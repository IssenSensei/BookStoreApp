package com.example.bookstoreapp.userQuotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.activity_user_quote_details.*

class UserQuoteDetailActivity : AppCompatActivity() {

    private lateinit var currentTheme: String
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
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
