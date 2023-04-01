package com.example.xo.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class LoginViewModel : ViewModel() {

    val Tag = "LoginViewModel"
    private val _emailId = mutableStateOf("")
    var emailId = _emailId

    private val _pwd = mutableStateOf("")
    var pwd = _pwd

    fun onEmailChange(email: String) {
        Log.d(Tag, email)
        _emailId.value = email
    }

    fun onPwdChange(pwd: String) {
        Log.d(Tag, pwd)
        _pwd.value = pwd
    }

    fun onLoginClick() {
        Log.d(Tag, _pwd.value + " " + _emailId.value)
    }

    fun onGoogleClick() {
        Log.d(Tag, "onGoogleClick")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }
}
