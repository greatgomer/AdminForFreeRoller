package com.svdgroup.adminforinline

import android.content.ContentValues
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class CitiesList() {
    private lateinit var database: DatabaseReference
    var citiesNames = emptyArray<String>()
    private var cities = "Cities"

    fun getNames(){
        database = AppDatabase.getDatabase()!!.getReference(cities)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (fieldName in dataSnapshot.children) {
                    val name = fieldName.child("name_en").getValue(String::class.java)
                    citiesNames += name.toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(ContentValues.TAG, databaseError.message) //Don't ignore errors!
            }
        }
        database.addListenerForSingleValueEvent(valueEventListener)
    }
}