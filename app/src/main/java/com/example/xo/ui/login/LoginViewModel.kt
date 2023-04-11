package com.example.xo.ui.login

import android.text.TextUtils
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginViewModel : ViewModel() {

    val Tag = "LoginViewModel"
    private val _emailId = mutableStateOf("")
    var emailId = _emailId

    private val _pwd = mutableStateOf("")
    var pwd = _pwd

    private var mAuth = FirebaseAuth.getInstance()

    private val _currentUser = mutableStateOf<FirebaseUser?>(null)
    var currentUser = _currentUser

    private val _logInState = mutableStateOf<LoginStatus>(LoginStatus.SHOW_LOADER)
    var logInState = _logInState

    init {
        checkLoginStatus()
    }

    fun onEmailChange(email: String) {
        _emailId.value = email
    }

    fun onPwdChange(pwd: String) {
        _pwd.value = pwd
    }

    fun onRegisterClick() {
        registerNewUser()
    }
    fun onLoginClick() {
         _logInState.value = LoginStatus.BYPASS_LOGIN
        //loginUser()
    }

    fun onGoogleClick() {
        Log.d(Tag, "onGoogleClick")
    }


    private fun checkLoginStatus() {
        _currentUser.value = mAuth.currentUser
        if (_currentUser.value == null) {
            _logInState.value = LoginStatus.SHOW_LOGIN_PAGE
        } else {
            _logInState.value = LoginStatus.ALREADY_LOGIN
        }
    }


   private fun loginUser() {
        _logInState.value = LoginStatus.SHOW_LOADER
       if (_emailId.value.isNullOrBlank() || _pwd.value.isNullOrBlank()) {
           _logInState.value = LoginStatus.EMAIL_NOT_VALID
           return
       }
        if (TextUtils.isEmpty(_emailId.value)) {
            _logInState.value = LoginStatus.EMAIL_NOT_VALID
            return
        }
        if (TextUtils.isEmpty(_pwd.value)) {
            _logInState.value = LoginStatus.PWD_NOT_VALID
            return
        }

        mAuth.signInWithEmailAndPassword(_emailId.value, _pwd.value)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _logInState.value = LoginStatus.LOGIN_SUC
                } else {
                    _logInState.value = LoginStatus.LOGIN_FAILED
                }
            }

    }


   private  fun registerNewUser() {
       _logInState.value = LoginStatus.SHOW_LOADER
       if (_emailId.value.isNullOrBlank() || _pwd.value.isNullOrBlank()) {
           _logInState.value = LoginStatus.EMAIL_NOT_VALID
           return
       }
       if (TextUtils.isEmpty(_emailId.value)) {
            _logInState.value = LoginStatus.EMAIL_NOT_VALID
           return
        }
        if (TextUtils.isEmpty(_pwd.value)) {
            _logInState.value = LoginStatus.PWD_NOT_VALID
            return
        }
        mAuth.createUserWithEmailAndPassword(_emailId.value, _pwd.value)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    _logInState.value = LoginStatus.REGISTER_SUC
                } else {
                    _logInState.value = LoginStatus.REGISTER_FAIL
                }
            })
    }

}
