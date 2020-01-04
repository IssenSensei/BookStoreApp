package com.example.bookstoreapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.utils.SharedPreference

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var sharedPreference: SharedPreference
    private lateinit var currentTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
        super.onCreate(savedInstanceState)
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

    override fun onResume() {
        super.onResume()
        val theme = sharedPreference.getValueString("current_theme")
        if(currentTheme != theme)
            recreate()
    }
}