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
import com.example.xo.ui.theme.XOTheme

@Composable
fun Main3X3() {
    gridView()

}

@Composable
fun GridButton() {


    Button(modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
        onClick = { /*TODO*/ }) {
        Text(text = "X", fontSize = 20.sp)


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
            Main3X3()
        }
    }
}


@Composable
fun gridView() {
     var courseList= listOf("1","2","3","4","5","6","7","8","9")
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(10.dp)
    ) {
        items(courseList.size) {
            GridButton()

        }
    }
}


