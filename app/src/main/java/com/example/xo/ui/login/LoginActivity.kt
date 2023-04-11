package com.example.xo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.xo.MainActivity
import com.example.xo.ui.theme.XOTheme

class LoginActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel
    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContent {

            XOTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    when (viewModel.logInState.value) {
                        LoginStatus.SHOW_LOADER -> {
                            Box(
                                modifier = Modifier.fillMaxSize(0.1f),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        LoginStatus.ALREADY_LOGIN -> {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                        }
                        LoginStatus.SHOW_LOGIN_PAGE -> {
                            LoginPage(viewModel)
                        }
                        LoginStatus.BYPASS_LOGIN -> {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                            SnackBarExample("BYPASS_LOGIN")
                        }
                        LoginStatus.LOGIN_SUC -> {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                            SnackBarExample("LOGIN_SUC")
                        }
                        LoginStatus.LOGIN_FAILED -> {
                            SnackBarExample("LOGIN_FAILED")
                        }
                        LoginStatus.REGISTER_SUC -> {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                            SnackBarExample("REGISTER_SUC")
                        }
                        LoginStatus.REGISTER_FAIL -> {
                            SnackBarExample("REGISTER_FAIL")
                        }
                        LoginStatus.PWD_NOT_VALID -> {
                            SnackBarExample("PWD_NOT_VALID")
                        }
                        LoginStatus.EMAIL_NOT_VALID -> {
                            SnackBarExample("EMAIL_NOT_VALID")
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
}

