package com.example.bookstoreapp.menu

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import kotlinx.android.synthetic.main.app_settings_activity_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppSettingsActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
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
            //todo
        }
        second_theme_button.setOnClickListener {
            //todo
        }
        third_theme_button.setOnClickListener {
            //todo
        }
        fourth_theme_button.setOnClickListener {
            //todo
        }
    }

}
