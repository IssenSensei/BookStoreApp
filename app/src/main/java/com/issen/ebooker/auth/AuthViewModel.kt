package com.issen.ebooker.auth

import android.app.Application
import androidx.lifecycle.*
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.issen.ebooker.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    val bookScope = "https://www.googleapis.com/auth/books"
    private var eBookerApplication: Application

    private val _accessTokenObtained = MutableLiveData<Boolean>()
    val accessTokenObtained: LiveData<Boolean>
        get() = _accessTokenObtained

    init {
        _accessTokenObtained.value = false
        eBookerApplication = application
    }

    fun getAccessToken(account: GoogleSignInAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            BaseActivity.token =
                GoogleAuthUtil.getToken(eBookerApplication.applicationContext, account.account, "oauth2:$bookScope")
        }
        _accessTokenObtained.value = true
    }

    fun clearAccessTokenObtained(){
        _accessTokenObtained.value = false
    }
}