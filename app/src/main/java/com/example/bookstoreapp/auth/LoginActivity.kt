package com.example.bookstoreapp.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.database.ApiInterface.Companion.RECOVERY_CODE
import com.example.bookstoreapp.database.ApiInterface.Companion.REGISTER_CODE
import com.example.bookstoreapp.user.UserItem
import com.example.bookstoreapp.utils.AppExecutors
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity() {
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appExecutors = AppExecutors()

        setContentView(R.layout.activity_login)

        login_login_button.setOnClickListener {
            login(login_login.text.toString(), login_password.text.toString())
        }

        login_register_link.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivityForResult(register, REGISTER_CODE)
        }

        login_recover_password.setOnClickListener {
            val intent = Intent(this, EmailRecoveryActivity::class.java)
            startActivityForResult(intent, RECOVERY_CODE)
        }
    }

    private fun login(login: String, password: String) {
        val apiInterface = ApiInterface.create()
            .getUserId("getUserId", login, password)

        apiInterface.enqueue(object : Callback<List<UserItem>> {

            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>?
            )
            {
                if (response?.body() != null) {
                    ApiInterface.USER_ID = response.body()!![0].id
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<UserItem>>?, t: Throwable?) {
                Toast.makeText(baseContext, "Błąd przy logowaniu, sprawdź połączenie z internetem oraz" +
                        "poprawność wprowadzonych danych", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RECOVERY_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(baseContext, "Mail został wysłany, sprawdź skrzynkę odbiorczą", Toast.LENGTH_LONG)
                .show()
        }
    }

}

