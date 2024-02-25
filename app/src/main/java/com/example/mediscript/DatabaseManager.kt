package com.example.mediscript

import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class FirebaseDBManager {

    private val database = FirebaseDatabase.getInstance().reference.child("reminders")

    fun addReminder(reminder: CounterClass) {
        val key = database.push().key
        key?.let {
            reminder.id = key
            database.child(key).setValue(reminder)
        }
    }


}