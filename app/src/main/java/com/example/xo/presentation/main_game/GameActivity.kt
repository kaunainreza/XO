package com.example.xo.presentation.main_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.xo.presentation.main_game.GameScreen

class GameActivity : ComponentActivity() {
    private lateinit var viewModel: GameViewModel
    private val TAG = "GameActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        setContent {
            GameScreen(viewModel = viewModel)

        }
    }
}

