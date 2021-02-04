package com.issen.ebooker.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.issen.ebooker.BaseActivity
import com.issen.ebooker.MainActivity
import com.issen.ebooker.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private val signInListener = View.OnClickListener {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(baseContext, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
        resultLauncher.launch(Intent(googleSignInClient.signInIntent))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener(signInListener)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            launchMain()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    launchMain()
                } else {
                    Toast.makeText(baseContext, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun launchMain() {
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        resultLauncher.launch(Intent(this, MainActivity::class.java))
    }

}
//    lateinit var appExecutors: AppExecutors
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        appExecutors = AppExecutors()
//
//        setContentView(R.layout.activity_login)
//
//        login_login_button.setOnClickListener {
//            login(login_email.text.toString(), login_password.text.toString())
//        }
//
//        login_register_link.setOnClickListener {
//            val register = Intent(applicationContext, RegisterActivity::class.java)
//            startActivity(register)
//        }
//
//        login_recover_password.setOnClickListener {
//            val intent = Intent(this, EmailRecoveryActivity::class.java)
//            startActivityForResult(intent, RECOVERY_CODE)
//        }
//
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        finishAffinity()
//    }
//
//    private fun login(email: String, password: String) {
//        val apiInterface = ApiInterface.create()
//            .getUserId("getUserId", email, password)
//        apiInterface.enqueue(object : Callback<Int> {
//
//            override fun onResponse(
//                call: Call<Int>,
//                response: Response<Int>
//            )
//            {
//                if (response.body() != null) {
//                    ApiInterface.USER_ID = response.body() as Int
//                    val intent = Intent(applicationContext, MainActivity::class.java)
//                    startActivity(intent)
//                }
//            }
//
//            override fun onFailure(call: Call<Int>?, t: Throwable?) {
//                Toast.makeText(baseContext, "Błąd przy logowaniu, sprawdź połączenie z internetem oraz " +
//                        "poprawność wprowadzonych danych", Toast.LENGTH_LONG)
//                    .show()
//            }
//        })
//    }


