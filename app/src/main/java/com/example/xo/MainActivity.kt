package com.example.xo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.xo.ui.compose.GameScreen
import com.example.xo.ui.data.GameViewModel
import com.example.xo.ui.theme.XOTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContent {
            GameScreen(viewModel = viewModel)

        }
    }
}

