package com.example.xo.ui

import android.util.Log
import androidx.annotation.NonNull
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.xo.ui.compose.GameEvent
import com.google.firebase.database.*


class GameViewModel : ViewModel() {
    private val TAG = "GameViewModel"
    private val firebaseDatabase = FirebaseDatabase.getInstance();
    private val databaseReference = firebaseDatabase.getReference("Players");

    private val PLAYER_X = "X"
    private val PLAYER_O = "O"
    private val CURRENT_PLAYER = PLAYER_X

    var gameEvents = mutableStateListOf<GameEvent>()

    init {
        getData(databaseReference)

    }


    fun onGameButtonClicked(itemId: Int) {
        Log.d(TAG, itemId.toString())
        val empObj = GameEvent(buttonId = itemId, playerId = CURRENT_PLAYER);
        gameEvents.add(empObj)
        addData(databaseReference)
    }

    private fun addData(databaseReference: DatabaseReference) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                databaseReference.setValue(gameEvents)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }


    public fun getData(databaseReference: DatabaseReference) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                val value = snapshot.value as Collection<GameEvent>?
                Log.d(TAG, value.toString())
                gameEvents.clear()

                value?.let { gameEvents.addAll(it) }


            }


            override fun onCancelled(@NonNull error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }
}