package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.Api

import org.json.JSONException
import org.json.JSONObject

import java.util.HashMap as HashMap


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginEt = findViewById<EditText>(R.id.loginField)
        val passwordEt = findViewById<EditText>(R.id.passwordField)

        val registerLink = findViewById<TextView>(R.id.registerLink)

        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val params = HashMap<String, String>()
            params["login"] = loginEt.text.toString()
            params["password"] = passwordEt.text.toString()
            login(params)
        }

        registerLink.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }
    }


    private fun login(params: HashMap<String, String>) {
        val stringRequest = StringRequest(Request.Method.GET ,
            Api.URL_GET_USER_ID+ "&login=" + params["login"] + "&password=" + params["password"],
            Response.Listener<String> { s ->
                try {
                    val obj = JSONObject(s)
                    if (!obj.getBoolean("error")) {
                        if (!obj.isNull("users")) {
                            Api.USER_ID = obj.getInt("users")
                            val intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext,"Nieprawidłowy login lub hasło", Toast.LENGTH_SHORT).show()
                        }

                    }

                } catch (e: JSONException) {
                    Toast.makeText(baseContext, "Problem z połączeniem", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }, Response.ErrorListener { volleyError -> Toast.makeText(baseContext, volleyError.message, Toast.LENGTH_LONG).show() })
        val requestQueue = Volley.newRequestQueue(baseContext)
        requestQueue.add<String>(stringRequest)
    }


}

