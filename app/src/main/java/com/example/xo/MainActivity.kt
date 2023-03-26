package com.example.xo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.xo.ui.GameViewModel
import com.example.xo.ui.compose.Main3X3
import com.example.xo.ui.theme.XOTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContent {

            XOTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main3X3(viewModel) {
                        viewModel.onGameButtonClicked(it)
                    }
                }
            }
        }
    }
}

