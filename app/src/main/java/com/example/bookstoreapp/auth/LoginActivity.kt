package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.user.UserItem
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.bookstoreapp.R.layout.activity_login)

        val login = findViewById<EditText>(com.example.bookstoreapp.R.id.login_login)
        val password = findViewById<EditText>(com.example.bookstoreapp.R.id.login_password)

        login_login_button.setOnClickListener {
            login(login.text.toString(), password.text.toString())
        }

        login_register_link.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }
    }

    private fun login(login: String, password: String) {
        val apiInterface = ApiInterface.create()
            .getUserId("getUserId", login, password)

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


//        login_recover_password.setOnClickListener {
//            Log.d("email", "qqqqqqqq")
//
//            getEmail(login.text.toString())
//        }
    //   }

//    private fun sendEmail(email: String) {
//        BackgroundMail.newBuilder(this)
//            .withUsername("username@gmail.com")
//            .withPassword("password12345")
//            .withMailto("toemail@gmail.com")
//            .withType(BackgroundMail.TYPE_PLAIN)
//            .withSubject("this is the subject")
//            .withBody("this is the body")
//            .withOnSuccessCallback {
//                //do some magic
//            }
//            .withOnFailCallback {
//                //do some magic
//            }
//            .send()
//
//    }

//    private fun getEmail(login: String) {
//
//        val apiInterface = ApiInterface.create().getEmail("getEmail",login )
//
//        apiInterface.enqueue(object : Callback<String> {
//
//            override fun onResponse(
//                call: Call<String>,
//                response: Response<String>?
//            ) {
//                if (response?.body() != null) {
//                    sendEmail(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<String>?, t: Throwable?) {
//            }
//        })
//    }
}

