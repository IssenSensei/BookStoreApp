package com.issen.ebooker.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.issen.ebooker.BaseActivity
import com.issen.ebooker.MainActivity
import com.issen.ebooker.R
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register_save_button.setOnClickListener {
//            register()
        }
        register_login_link.setOnClickListener {
            val login = Intent(applicationContext, LoginActivity::class.java)
            startActivity(login)
        }
    }

//    private fun register() {
//        val apiInterface = ApiInterface.create().createUser(
//            "createUser",
//            register_password.text.toString(),
//            register_name.text.toString(),
//            register_surname.text.toString(),
//            register_email.text.toString()
//        )
//        apiInterface.enqueue(object : Callback<Int> {
//
//            override fun onResponse(call: Call<Int>, response: Response<Int>) {
//                if (response.isSuccessful) {
//                    ApiInterface.USER_ID = response.body().toString().toInt()
//                    val intent = Intent(applicationContext, MainActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//            override fun onFailure(call: Call<Int>, t: Throwable?) {
//                Log.d("Wystąpił błąd, spróbuj ponownie później", t.toString())
//
//            }
//        })
//    }
}