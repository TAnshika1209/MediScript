package com.example.mediscript

import android.widget.Toast

class FirebaseDBManager {

    private val database = FirebaseDatabase.getInstance().reference.child("reminders")

    fun addReminder(reminder: CounterClass) {
        val key = database.push().key
        key?.let {
            reminder.id = key
            database.child(key).setValue(reminder)
        }
    }

    fun getAllReminders(callback: (List<CounterClass>) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val reminders = mutableListOf<CounterClass>()
                for (reminderSnapshot in snapshot.children) {
                    val reminder = reminderSnapshot.getValue(CounterClass::class.java)
                    reminder?.let { reminders.add(it) }
                }
                callback(reminders)
            }

            override fun onCancelled(error: DatabaseError){
                Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        })
    }
}