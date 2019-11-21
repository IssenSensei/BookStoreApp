package com.example.bookstoreapp.user

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.Api
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONException
import org.json.JSONObject

class UserProfileActivity : AppCompatActivity() {
    private lateinit var user: UserItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        loadUserData()
        val login = findViewById<EditText>(R.id.loginInput)!!
        val email = findViewById<EditText>(R.id.emailInput)!!
        val phone = findViewById<EditText>(R.id.phoneInput)!!
        val name = findViewById<EditText>(R.id.nameInput)!!
        val lastName = findViewById<EditText>(R.id.lastNameInput)!!

        user = UserItem(0, "login","password","name","lastName","email","845798458")

        login.setText(user.login)
        email.setText(user.email)
        phone.setText(user.phone)
        name.setText(user.name)
        lastName.setText(user.lastName)

        edit_profile_button.setOnClickListener{
            updateUser()
        }

        change_password_button.setOnClickListener{
            updatePassword()
        }
    }



    private fun loadUserData() {
        val stringRequest = StringRequest(Request.Method.GET,
            Api.URL_GET_USER,
            Response.Listener<String> { s ->
                try {

                    val obj = JSONObject(s)

                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("users")

                        for (i in 0..array.length() - 1) {
                            val objectUser = array.getJSONObject(i)
                            user = UserItem(
                                objectUser.getInt("id"),
                                objectUser.getString("login"),
                                objectUser.getString("password"),
                                objectUser.getString("name"),
                                objectUser.getString("lastName"),
                                objectUser.getString("email"),
                                objectUser.getString("phone")
                            )
                        }
                    } else {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add<String>(stringRequest)
    }

    private fun updateUser(){
        //todo user
    }

    private fun updatePassword(){
        //todo password
    }
}
