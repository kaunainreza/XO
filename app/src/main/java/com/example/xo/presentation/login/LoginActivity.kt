package com.example.xo.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.xo.presentation.main_game.GameActivity
import com.example.xo.presentation.theme.XOTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel
    private val TAG = "LoginActivity"

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        setContent {

            XOTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    LaunchedEffect(key1 = Unit) {
                        if (googleAuthUiClient.getSignedInUser() != null) {
                            //  navController.navigate("profile")
                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )
                    //---

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
                            val myIntent = Intent(this@LoginActivity, GameActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                        }

                        LoginStatus.SHOW_LOGIN_PAGE -> {
                            LoginPage(viewModel = viewModel) {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }

                            }
                        }

                        LoginStatus.BYPASS_LOGIN -> {
                            val myIntent = Intent(this@LoginActivity, GameActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                            SnackBarExample("BYPASS_LOGIN")
                        }

                        LoginStatus.LOGIN_SUC -> {
                            val myIntent = Intent(this@LoginActivity, GameActivity::class.java)
                            this@LoginActivity.startActivity(myIntent)
                            finishAffinity()
                            SnackBarExample("LOGIN_SUC")
                        }

                        LoginStatus.LOGIN_FAILED -> {
                            SnackBarExample("LOGIN_FAILED")
                        }

                        LoginStatus.REGISTER_SUC -> {
                            val myIntent = Intent(this@LoginActivity, GameActivity::class.java)
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