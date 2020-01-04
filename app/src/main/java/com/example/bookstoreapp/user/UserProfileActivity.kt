package com.example.bookstoreapp.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        loadUserData()
        profile_edit_button.setOnClickListener {
            updateUser(
                profile_login.text.toString(),
                profile_name.text.toString(),
                profile_lastName.text.toString(),
                profile_email.text.toString(),
                profile_phone.text.toString()
            )
        }


        profile_password_change_button.setOnClickListener {
            updatePassword(
                profile_password.text.toString(),
                profile_new_password.text.toString()
            )
        }
    }


    private fun loadUserData() {
        val apiInterface = ApiInterface.create().getUser("getUser", 3)

        apiInterface.enqueue(object : Callback<UserItem> {

            override fun onResponse(
                call: Call<UserItem>,
                response: Response<UserItem>?
            ) {
                if (response?.body() != null) {
                    Log.d("profile", response.body().toString())
                    profile_login.setText(response.body()!!.login)
                    profile_email.setText(response.body()!!.email)
                    profile_phone.setText(response.body()!!.phone)
                    profile_name.setText(response.body()!!.name)
                    profile_lastName.setText(response.body()!!.lastName)
                }
            }

            override fun onFailure(call: Call<UserItem>?, t: Throwable?) {
            }
        })
    }

    private fun updateUser(
        login: String,
        name: String,
        lastName: String,
        email: String,
        phone: String
    ) {
        val apiInterface =
            ApiInterface.create().updateUser("updateUser", 3, login, name, lastName, email, phone)

        apiInterface.enqueue(object : Callback<Int> {

            override fun onResponse(
                call: Call<Int>,
                response: Response<Int>?
            ) {
                if (response?.body() != null) {
                    Log.d("profilerrrr", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Int>?, t: Throwable?) {
            }
        })
    }

    private fun updatePassword(password: String, newPassword: String) {
        val apiInterface =
            ApiInterface.create().changePassword("changePassword", password, newPassword, 2)

        apiInterface.enqueue(object : Callback<Int> {

            override fun onResponse(
                call: Call<Int>,
                response: Response<Int>?
            ) {
                if (response?.body() != null) {
                    Log.d("profilerrrrpppp", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Int>?, t: Throwable?) {

            }
        })
    }
}

