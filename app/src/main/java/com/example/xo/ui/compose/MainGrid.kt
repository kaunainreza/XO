package com.example.xo.ui.compose

import android.util.Log
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
import com.example.xo.ui.theme.XOTheme

@Composable
fun Main3X3(buttonList: MutableList<String>, itemClicked: (Int) -> Unit) {
    gridView(buttonList, itemClicked)
}

@Composable
fun gridView(buttonList: MutableList<String>, itemClicked: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(10.dp)
    ) {
        items(buttonList.size) {
            GridButton(it, itemClicked)
        }
    }
}

@Composable
fun GridButton(
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
        Text(text = "", fontSize = 20.sp)
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
            var buttonList = mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
            Main3X3(buttonList, itemClicked = {
                Log.d("Main3X3Preview : ItemClicked",it.toString())
            })
        }
    }
}


