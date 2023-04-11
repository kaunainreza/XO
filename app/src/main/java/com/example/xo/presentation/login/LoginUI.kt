package com.example.xo.presentation.login


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xo.R
import com.example.xo.presentation.theme.XOTheme


@Composable
fun LoginPage(viewModel: LoginViewModel? = null) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))

                Text(
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                EmailEditTextField(viewModel)

                Spacer(modifier = Modifier.padding(3.dp))
                PasswordTextField(viewModel)

                Spacer(modifier = Modifier.padding(10.dp))
                LoginButton(
                    viewModel,
                    nameButton = "Login",
                )

                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = {
                    viewModel?.onGoogleClick()

                }) {
                    Text("Sign in via Google")
                }

                Spacer(modifier = Modifier.padding(10.dp))
                RegisterNow(viewModel = viewModel, nameButton = "Register Now?")

            }
        }
    }
}


//email id
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailEditTextField(viewModel: LoginViewModel?) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val text = viewModel?.emailId?.value ?: ""
    OutlinedTextField(
        value = text,
        onValueChange = {
            viewModel?.onEmailChange(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Name or Email Address",
            )
        },
        placeholder = { Text(text = "Name or Email Address") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(viewModel: LoginViewModel?) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordHidden by rememberSaveable { mutableStateOf(true) }
    val text = viewModel?.pwd?.value ?: ""
    OutlinedTextField(
        value = text,
        onValueChange = {
            viewModel?.onPwdChange(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Enter Password",
            )
        },
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
        ),
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}

@Composable
private fun LoginButton(
    viewModel: LoginViewModel?,
    nameButton: String,
) {

    Box(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(0.8f)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF5B5EF0),
                        Color(0xFFEE4A82)
                    )

                ),
                shape = RoundedCornerShape(30)
            )
            .clickable {
                viewModel?.onLoginClick()
            }, contentAlignment = Alignment.Center
    ) {
        Text(text = nameButton, fontSize = 22.sp)
    }

}

@Composable
fun RegisterNow(
    viewModel: LoginViewModel?,
    nameButton: String,
) {
    Box(
        modifier = Modifier
            .height(36.dp)
            .fillMaxWidth(0.7f)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF797BE9),
                        Color(0xFFE7C6D1)
                    )

                ),
                shape = RoundedCornerShape(30)
            )
            .clickable {
                viewModel?.onRegisterClick()
            }, contentAlignment = Alignment.Center
    ) {
        Text(text = nameButton, fontSize = 14.sp, color = Color.White)
    }

}


@Composable
fun SnackBarExample(msg: String) {
    var snackbarVisible by remember { mutableStateOf(false) }

    if (snackbarVisible) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
        ) {

            Text(text = msg)
        }
    }

}


@Preview
@Composable
fun pev() {
    XOTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginPage()
        }
    }
}