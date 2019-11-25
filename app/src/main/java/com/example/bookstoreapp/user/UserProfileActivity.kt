package com.example.bookstoreapp.user

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback

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

       val apiInterface = ApiInterface.create().getUser("getUser", 3)

       apiInterface.enqueue( object : Callback<List<UserItem>> {

           override fun onResponse(call: Call<List<UserItem>>, response: retrofit2.Response<List<UserItem>>?) {
               if(response?.body() != null) {
                   user = UserItem(
                        response.body()!![0].id,
                        response.body()!![0].login,
                        response.body()!![0].password,
                        response.body()!![0].name,
                        response.body()!![0].lastName,
                        response.body()!![0].email,
                        response.body()!![0].phone
                   )
               }
           }

           override fun onFailure(call: Call<List<UserItem>>?, t: Throwable?) {
               Toast.makeText(baseContext, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()

           }
       })
   }
//        val stringRequest = StringRequest(Request.Method.GET,
//            Api.URL_GET_USER,
//            Response.Listener<String> { s ->
//                try {
//
//                    val obj = JSONObject(s)
//
//                    if (!obj.getBoolean("error")) {
//
//                        val array = obj.getJSONArray("users")
//
//                        for (i in 0..array.length() - 1) {
//                            val objectUser = array.getJSONObject(i)
//                            user = UserItem(
//                                objectUser.getInt("id"),
//                                objectUser.getString("login"),
//                                objectUser.getString("password"),
//                                objectUser.getString("name"),
//                                objectUser.getString("lastName"),
//                                objectUser.getString("email"),
//                                objectUser.getString("phone")
//                            )
//                        }
//                    } else {
//                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
//                    }
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            }, Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })
//
//        val requestQueue = Volley.newRequestQueue(applicationContext)
//        requestQueue.add<String>(stringRequest)
//    }

    private fun updateUser(){
        //todo user
    }

    private fun updatePassword(){
        //todo password
    }
}
