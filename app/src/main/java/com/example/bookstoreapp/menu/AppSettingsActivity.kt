package com.example.bookstoreapp.menu

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
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
            val apiInterface = ApiInterface.create().deleteUser("deleteUser", ApiInterface.USER_ID)

            apiInterface.enqueue(object : Callback<Int> {

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    if(response.isSuccessful) {
                        Toast.makeText(baseContext, "Konto zostało usunięte", Toast.LENGTH_LONG)
                            .show()
                        ApiInterface.USER_ID = -1
                    }
                }

                override fun onFailure(call: Call<Int>, t: Throwable?) {
                    Toast.makeText(baseContext, "Błąd podczas usuwania konta, spróbuj ponownie później", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }

        first_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_WHITISH")
            recreate()
        }
        second_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_DARKISH")
            recreate()
        }
        third_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_PURPLISH")
            recreate()
        }
        fourth_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_GREENISH")
            recreate()
        }
        fifth_theme_button.setOnClickListener {
            sharedPreference.save("current_theme", "THEME_FULLWHITE")
            recreate()
        }
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
