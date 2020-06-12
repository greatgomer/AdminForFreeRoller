package com.svdgroup.adminforinline

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_trains.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    fun add_marker(view: View) {
        val intent = Intent(this, MarkersActivity::class.java)
        startActivity(intent)
    }
    fun add_train(view: View) {
        val intent = Intent(this, TrainsActivity::class.java)
        startActivity(intent)
    }

    fun add_city(view: View) {
        val intent = Intent(this, AddCityActivity::class.java)
        startActivity(intent)
    }

    fun add_group(view: View) {
        val intent = Intent(this, AddGroupActivity::class.java)
        startActivity(intent)
    }
}