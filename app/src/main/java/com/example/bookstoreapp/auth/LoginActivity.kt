package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.Api
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.user.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEt = findViewById<EditText>(R.id.loginField)
        val passwordEt = findViewById<EditText>(R.id.passwordField)

        val registerLink = findViewById<TextView>(R.id.registerLink)

        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            login(loginEt.text.toString(), passwordEt.text.toString())
        }

        registerLink.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }
    }


    private fun login(login: String, password: String) {
//        val stringRequest = StringRequest(Request.Method.GET ,
//            Api.URL_GET_USER_ID+ "&login=" + params["login"] + "&password=" + params["password"],
//            Response.Listener<String> { s ->
//                try {
//                    val obj = JSONObject(s)
//                    if (!obj.getBoolean("error")) {
//                        if (!obj.isNull("users")) {

//                        } else {
//                            Toast.makeText(applicationContext,"Nieprawidłowy login lub hasło", Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//
//                } catch (e: JSONException) {
//                    Toast.makeText(baseContext, "Problem z połączeniem", Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }, Response.ErrorListener { volleyError -> Toast.makeText(baseContext, volleyError.message, Toast.LENGTH_LONG).show() })
//        val requestQueue = Volley.newRequestQueue(baseContext)
//        requestQueue.add<String>(stringRequest)
//    }

        val apiInterface = ApiInterface.create().getUserId("getUserId", login, password)

        apiInterface.enqueue(object : Callback<List<UserItem>> {

            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>?
            ) {
                if (response?.body() != null) {
                    Api.USER_ID = response.body()!![0].id
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

