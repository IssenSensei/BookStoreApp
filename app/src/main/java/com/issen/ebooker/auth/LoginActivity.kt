package com.issen.ebooker.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.issen.ebooker.BaseActivity
import com.issen.ebooker.MainActivity
import com.issen.ebooker.R
import com.issen.ebooker.database.ApiInterface
import com.issen.ebooker.database.ApiInterface.Companion.RECOVERY_CODE
import com.issen.ebooker.utils.AppExecutors
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
            login(login_email.text.toString(), login_password.text.toString())
        }

        login_register_link.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }

        login_recover_password.setOnClickListener {
            val intent = Intent(this, EmailRecoveryActivity::class.java)
            startActivityForResult(intent, RECOVERY_CODE)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun login(email: String, password: String) {
        val apiInterface = ApiInterface.create()
            .getUserId("getUserId", email, password)
        apiInterface.enqueue(object : Callback<Int> {

            override fun onResponse(
                call: Call<Int>,
                response: Response<Int>
            )
            {
                if (response.body() != null) {
                    ApiInterface.USER_ID = response.body() as Int
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Int>?, t: Throwable?) {
                Toast.makeText(baseContext, "Błąd przy logowaniu, sprawdź połączenie z internetem oraz " +
                        "poprawność wprowadzonych danych", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}

