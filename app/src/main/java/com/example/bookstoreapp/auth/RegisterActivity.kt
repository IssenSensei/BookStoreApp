package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_save_button.setOnClickListener {
            register()
        }
        register_login_link.setOnClickListener {
            val login = Intent(applicationContext, LoginActivity::class.java)
            startActivity(login)
        }
    }

    private fun register() {
        val apiInterface = ApiInterface.create().createUser(
            "createUser",
            register_login.text.toString(),
            register_password.text.toString(),
            register_name.text.toString(),
            register_lastName.text.toString(),
            register_email.text.toString(),
            register_phone.text.toString(),
            status = "user"
        )
        apiInterface.enqueue(object : Callback<Int> {

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    ApiInterface.USER_ID = response.body().toString().toInt()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<Int>, t: Throwable?) {
                Log.d("qpablad", t.toString())

            }
        })
    }
}