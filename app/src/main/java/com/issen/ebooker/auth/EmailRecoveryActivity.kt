package com.issen.ebooker.auth

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.issen.ebooker.BaseActivity
import com.issen.ebooker.R
import com.issen.ebooker.database.ApiInterface
import com.issen.ebooker.utils.AppExecutors
import kotlinx.android.synthetic.main.activity_email_recovery.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EmailRecoveryActivity : BaseActivity() {
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appExecutors = AppExecutors()

        setContentView(R.layout.activity_email_recovery)
        initToolbar()

        recovery_send_button.setOnClickListener {
            sendRecoveryEmail(recovery_email_field.text.toString())
        }

        recovery_cancel_button.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun sendRecoveryEmail(email: String){
        val password = UUID.randomUUID().toString().substring(0,7)

        val apiInterface = ApiInterface.create().recoverPassword("recoverPassword", password, email)
        apiInterface.enqueue(object : Callback<Int> {
            override fun onResponse(
                call: Call<Int>,
                response: Response<Int>?
            ) {
                if (response?.body() != null) {
                    appExecutors.diskIO().execute {
                        val props = System.getProperties()
                        props["mail.smtp.host"] = "smtp.gmail.com"
                        props["mail.smtp.socketFactory.port"] = "465"
                        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
                        props["mail.smtp.auth"] = "true"
                        props["mail.smtp.port"] = "465"

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
                                InternetAddress(email)
                            )
                            mm.subject = "Pomoc z odzyskiwaniem hasła BookStoreApp"
                            mm.setText("Witaj, \nna Twoje konto zostało wysłane zapytanie o hasło do konta. " +
                                    "Twoje nowe hasło to \n\n\t $password \n\n Użyj go aby zalogować się do serwisu." +
                                    "Pamiętaj, że zawsze możesz zmienić hasło menu głównym, w oknie edycki profiliu.")
                            Transport.send(mm)
                            appExecutors.mainThread().execute {}
                            setResult(Activity.RESULT_OK)
                            finish()
                        } catch (e: MessagingException) {
                            e.printStackTrace()
                            Toast.makeText(baseContext, "Twoje hasło zostało zmienione, lecz mail nie został wysłany," +
                                    " sprawdź połączenie z internetem" +
                                    "lub spróbuj ponownie później", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Int>?, t: Throwable?) {
                Toast.makeText(baseContext, "Wystąpił błąd, sprawdź połączenie z internetem lub spróbuj ponownie później", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.app_info_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            setResult(Activity.RESULT_CANCELED)
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}