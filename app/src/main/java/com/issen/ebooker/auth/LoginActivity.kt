package com.issen.ebooker.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.issen.ebooker.BaseActivity
import com.issen.ebooker.MainActivity
import com.issen.ebooker.R
import kotlinx.android.synthetic.main.activity_login.*


//todo fast made auth solution, may be full of errors and is inelegant, to fix
class LoginActivity : BaseActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth
    private val viewModel = AuthViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Scope(viewModel.bookScope))
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        viewModel.accessTokenObtained.observe(this, Observer {
            if (it) {
                launchMain()
            }
        })

        sign_in_button.setOnClickListener {
            val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleSignInResult(task)
            }
            resultLauncher.launch(Intent(googleSignInClient.signInIntent))
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            viewModel.getAccessToken(account)
        }
    }

//
//    googleSignInClient.silentSignIn()
//    .addOnFailureListener {
//        sign_in_button.setOnClickListener {
//
//        }
//    }
//    .addOnCompleteListener {
//        handleSignInResult(it)
//    }
//    auth = Firebase.auth

    private fun handleSignInResult(task: Task<GoogleSignInAccount?>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account)
            viewModel.getAccessToken(account)
        } catch (e: ApiException) {
            Toast.makeText(baseContext, "Google sign in failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val idToken = account.idToken
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    viewModel.getAccessToken(account)
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



