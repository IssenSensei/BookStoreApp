package com.example.bookstoreapp.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.bookstoreapp.R
import com.example.bookstoreapp.auth.LoginActivity
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
        initToolbar()


        delete_account_button.setOnClickListener {
            val apiInterface = ApiInterface.create().deleteUser("deleteUser", ApiInterface.USER_ID)

            apiInterface.enqueue(object : Callback<Boolean> {

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    if(response.isSuccessful) {
                        Toast.makeText(baseContext, "Konto zostało usunięte", Toast.LENGTH_LONG)
                            .show()
                        ApiInterface.USER_ID = -1
                        val intent = Intent(this@AppSettingsActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable?) {
                    Toast.makeText(baseContext, "Błąd podczas usuwania konta, spróbuj ponownie później $t", Toast.LENGTH_LONG)
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

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.collapsing_toolbar)
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
