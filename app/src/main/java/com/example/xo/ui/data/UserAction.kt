package com.example.xo.ui.data

sealed class UserAction{
    object PlayAgainButtonClicked: UserAction()
    data class BoardTapped(val cellNo:Int):UserAction()
}
