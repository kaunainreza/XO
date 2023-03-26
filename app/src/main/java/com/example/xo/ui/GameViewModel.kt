package com.example.xo.ui

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import com.example.xo.ui.compose.Players
import com.google.firebase.database.*


class GameViewModel : ViewModel() {
    private val TAG = "GameViewModel"
    private val firebaseDatabase = FirebaseDatabase.getInstance();
    private val databaseReference = firebaseDatabase.getReference("Players");

    init {
        addData(databaseReference)
        getData(databaseReference)
    }

    private fun addData(databaseReference: DatabaseReference) {
        val empObj = Players("En", "Ec", "Add");
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                databaseReference.setValue(empObj)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }


    private fun getData(databaseReference: DatabaseReference) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                val value = snapshot.getValue(Players::class.java)
                Log.d(TAG, value.toString())
            }

            override fun onCancelled(@NonNull error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }
}