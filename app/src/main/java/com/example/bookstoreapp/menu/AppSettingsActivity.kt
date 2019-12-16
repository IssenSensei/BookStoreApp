package com.example.bookstoreapp.menu

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.SharedPreference
import kotlinx.android.synthetic.main.app_settings_activity_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppSettingsActivity: AppCompatActivity(){
    private lateinit var sharedPreference: SharedPreference
    private lateinit var currentTheme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_settings_activity_layout)


        delete_account_button.setOnClickListener {
            val apiInterface = ApiInterface.create().deleteUser("deleteUser", 4.toString())

            apiInterface.enqueue(object : Callback<Int> {

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        Log.i("addresponse", "post submitted to API." + response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable?) {
                    Log.d("qpablad", t.toString())

                }
            })
        }

        first_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_GREENISH")
            Log.d("colors", "first_theme_click")
            recreate()
        }
        second_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_PURPLISH")
            Log.d("colors", "second_theme_click")
            recreate()
        }
        third_theme_button.setOnClickListener {
            //todo
        }
        fourth_theme_button.setOnClickListener {
            //todo
        }
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            "THEME_GREENISH" -> setTheme(R.style.Theme_App_Greenish)
            else -> setTheme(R.style.Theme_App_Purplish)
        }
    }

        override fun onResume() {
        super.onResume()
        val theme = sharedPreference.getValueString("current_theme")
        if(currentTheme != theme)
            recreate()
    }

}
