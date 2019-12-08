package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.user.UserItem
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<EditText>(R.id.login_login)
        val password = findViewById<EditText>(R.id.login_password)

        login_login_button.setOnClickListener {
            login(login.text.toString(), password.text.toString())
        }

        login_register_link.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }

        login_forgotten_password.setOnClickListener {
            //todo
        }
    }


    private fun login(login: String, password: String) {
        val apiInterface = ApiInterface.create().getUserId("getUserId", login, password)

        apiInterface.enqueue(object : Callback<List<UserItem>> {

            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>?
            ) {
                if (response?.body() != null) {
                    ApiInterface.USER_ID = response.body()!![0].id
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<UserItem>>?, t: Throwable?) {
                Toast.makeText(baseContext, "Błąd przy logowaniu", Toast.LENGTH_SHORT)
                    .show()

            }
        })

    }
}

