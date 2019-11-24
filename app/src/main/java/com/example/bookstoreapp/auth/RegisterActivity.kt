package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.Api
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        saveButton.setOnClickListener {
            register()
        }

        loginLink.setOnClickListener {
            val login = Intent(applicationContext, LoginActivity::class.java)
            startActivity(login)
        }
    }

    private fun register() {
        val requestQueue = Volley.newRequestQueue(baseContext)

        var strRequest: StringRequest = object : StringRequest(
            Method.POST, Api.URL_CREATE_USER,
            Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
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
            },
            Response.ErrorListener { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = nameField.text.toString()
                params["lastName"] = lastNameField.text.toString()
                params["phone"] = phoneField.text.toString()
                params["login"] = loginField.text.toString()
                params["email"] = emailField.text.toString()
                params["password"] = passwordField.text.toString()
                params["status"] = "user"
                return params
            }
        }
        requestQueue.add<String>(strRequest)
    }
}