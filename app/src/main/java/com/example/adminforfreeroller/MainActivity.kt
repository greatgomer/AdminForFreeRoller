package com.example.adminforfreeroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var places_name = "Places"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().getReference(places_name)
    }

    fun saveData(view: View) {
        var id = database.getKey().toString()
        var lat = editText_lat.toString()
        var lon = editText_lon.toString()
        var name = editText_name.toString()
        var opt = editText_opt.toString()
        val addData = AddData(id, lat, lon, name, opt)
        database.push().setValue(addData)
    }
}
