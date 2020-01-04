package com.example.bookstoreapp.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.utils.SharedPreference

class NewsDetailActivity : BaseActivity() {

    private lateinit var currentTheme: String
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val data: NewsItem = intent.getSerializableExtra("data") as NewsItem

        val content = findViewById<TextView>(R.id.content)!!
        val title = findViewById<TextView>(R.id.register_title)!!
        val login = findViewById<TextView>(R.id.login)!!
        val picture = findViewById<ImageView>(R.id.image)!!

        content.text = data.content
        title.text = data.title
        login.text = data.login
        Glide.with(applicationContext)
            .load(data.picture)
            .into(picture)
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
