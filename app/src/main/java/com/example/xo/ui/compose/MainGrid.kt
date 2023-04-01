package com.example.xo.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xo.ui.GameViewModel
import com.example.xo.ui.theme.XOTheme


@Composable
fun Main3X3(viewModel: GameViewModel, itemClicked: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(10.dp)
    ) {
        items(9) { position ->

            val event = viewModel.gameEvents.firstOrNull { it.buttonId == position }
            val xo = event?.playerId ?: ""
            GridButton(xo, position, itemClicked)
        }
    }
}

@Composable
fun GridButton(
    playerXO: String = "",
    position: Int, itemClicked: (
        Int
    ) -> Unit
) {
    Button(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
        onClick = {
            itemClicked(position)

        },
    ) {
        Text(text = playerXO, fontSize = 20.sp)
    }
}



@Preview
@Composable
fun Main3X3Preview() {
    XOTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            /* var gameEvents = mutableStateListOf<GameEvent>()
             Main3X3(gameEvents, itemClicked = {
                 Log.d("Main3X3Preview : ItemClicked", it.toString())
             })*/
        }
    }
}


