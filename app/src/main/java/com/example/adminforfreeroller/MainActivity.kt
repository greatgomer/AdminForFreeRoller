package com.example.adminforfreeroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        val sentData = findViewById<Button>(R.id.buttonSetData)
        val editLat = findViewById<EditText>(R.id.editText_lat)
        val editLon = findViewById<EditText>(R.id.editText_lon)
        val editName = findViewById<EditText>(R.id.editText_name)
        val editOpt = findViewById<EditText>(R.id.editText_opt)
        database = FirebaseDatabase.getInstance().getReference(places_name)

        sentData.setOnClickListener {
            var id = database.getKey().toString()
            var lat = editLat.text.toString()
            var lon = editLon.text.toString()
            var name = editName.text.toString()
            var opt = editOpt.text.toString()
            if(!lat.isEmpty() && !lon.isEmpty() && !name.isEmpty() && !opt.isEmpty()){
                val addData = AddData(id, lat, lon, name, opt)
                database.push().setValue(addData)
            }else{
                Toast.makeText(this, R.string.check_add, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
