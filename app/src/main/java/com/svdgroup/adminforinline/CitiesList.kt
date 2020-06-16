package com.svdgroup.adminforinline

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


class CitiesList() {
    private lateinit var database: DatabaseReference
    var citiesNames = emptyArray<Pair<String, String>>()
    private var cities = "Cities"
    private val citiesMutableLiveData = MutableLiveData<Array<Pair<String, String>>>()

    fun getNames(): MutableLiveData<Array<Pair<String, String>>> {
        database = AppDatabase.getDatabase()!!.getReference(cities)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (fieldName in dataSnapshot.children) {
                    val name = fieldName.child("name_en").getValue(String::class.java)
                    val city = Pair(fieldName.key.toString(), name.toString())
                    citiesNames += city
                }
                citiesMutableLiveData.value = citiesNames
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(ContentValues.TAG, databaseError.message) //Don't ignore errors!
            }
        }
        database.addListenerForSingleValueEvent(valueEventListener)
        return citiesMutableLiveData
    }
}