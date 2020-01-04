package com.example.bookstoreapp.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.BaseActivity
import com.example.bookstoreapp.MainActivity
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.user.UserItem
import com.example.bookstoreapp.utils.AppExecutors
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class LoginActivity : BaseActivity() {
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appExecutors = AppExecutors()

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

        login_recover_password.setOnClickListener {
            getEmail(login.text.toString())
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

    private fun getEmail(login: String) {

        val apiInterface = ApiInterface.create().getEmail("getEmail",login )

        apiInterface.enqueue(object : Callback<String> {

            override fun onResponse(
                call: Call<String>,
                response: Response<String>?
            ) {
                if (response?.body() != null) {
                    sendEmail(response.body().toString())
                }
            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
            }
        })
    }

    private fun sendEmail(email: String){
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session =  Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(AppExecutors.EMAIL, AppExecutors.PASSWORD)
                    }
                })

            try {
                val mm = MimeMessage(session)
                mm.setFrom(InternetAddress(AppExecutors.EMAIL))
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(email))
                mm.subject = "Pomoc z odzyskiwaniem hasła"
                mm.setText("Your mail body.")
                Transport.send(mm)
                appExecutors.mainThread().execute {}

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }
}

