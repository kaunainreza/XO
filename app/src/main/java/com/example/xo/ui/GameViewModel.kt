package com.example.xo.ui

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import com.example.xo.ui.compose.Players
import com.google.firebase.database.*


class GameViewModel : ViewModel() {
    val TAG = "GameViewModel"
    val firebaseDatabase = FirebaseDatabase.getInstance();
    val databaseReference = firebaseDatabase.getReference("EmployeeInfo");

    init {
        addData(databaseReference)
        getdata(databaseReference)
    }
    fun addData(databaseReference: DatabaseReference) {
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


    private fun getdata(databaseReference: DatabaseReference) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(@NonNull snapshot: DataSnapshot) {
                val value = snapshot.getValue(String::class.java)
                Log.d(TAG, value.toString())
            }

            override fun onCancelled(@NonNull error: DatabaseError) {
                Log.d(TAG, error.message)
            }
        })
    }
}