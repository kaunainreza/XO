/*
package com.example.xo.ui

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val TAG = "GameViewModel"
    private val firebaseDatabase = FirebaseDatabase.getInstance();
    private val databaseReference = firebaseDatabase.getReference("GameEvent");

    private val PLAYER_X = "X"
    private val PLAYER_O = "O"
    private val CURRENT_PLAYER = PLAYER_X

    var gameEventStateFlow: MutableLiveData<List<GameEvent>> = MutableLiveData()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData(databaseReference)
        }

    }


    fun onGameButtonClicked(itemId: Int) {
        Log.d(TAG, itemId.toString())
        val eventObj = GameEvent(buttonId = itemId.toString(), playerId = CURRENT_PLAYER);
        addData(databaseReference, eventObj)
    }

    private fun addData(databaseReference: DatabaseReference, eventObj: GameEvent) {
        val g: ArrayList<GameEvent> = ArrayList()
        g.add(eventObj)
        gameEventStateFlow.value?.let {
            g.addAll(it)
        }

        databaseReference.setValue(g)
    }


    private suspend fun getData(databaseReference: DatabaseReference) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                val animalList: MutableList<GameEvent> = ArrayList()
                for (ds in snapshot.children) {
                    ds.getValue<GameEvent>()?.let { animalList.add(it) }
                }

                gameEventStateFlow.value = animalList

            }


            override fun onCancelled(@NonNull error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }
}*/
