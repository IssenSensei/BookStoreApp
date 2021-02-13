package com.issen.ebooker.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.issen.ebooker.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(private val context: Context) : ViewModel() {
    val bookScope = "https://www.googleapis.com/auth/books"

    private val _accessTokenObtained = MutableLiveData<Boolean>()
    val accessTokenObtained: LiveData<Boolean>
    get() = _accessTokenObtained

    init {
        _accessTokenObtained.value = false
    }

    fun getAccessToken(account: GoogleSignInAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            BaseActivity.token = GoogleAuthUtil.getToken(context, account.account, "oauth2:$bookScope")
        }
        _accessTokenObtained.value = true
    }
}