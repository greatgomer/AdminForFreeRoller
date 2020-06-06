package com.example.adminforfreeroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
        val editCity = findViewById<EditText>(R.id.editText_city)

        database = FirebaseDatabase.getInstance().getReference(places_name)

        sentData.setOnClickListener {
            var id = database.getKey().toString()
            var lat = editLat.text.toString()
            var lon = editLon.text.toString()
            var name = editName.text.toString()
            var opt = editOpt.text.toString()
            var city = editCity.text.toString()
            database = FirebaseDatabase.getInstance().getReference(places_name).child(city)
            if(!lat.isEmpty() && !lon.isEmpty() && !name.isEmpty() && !opt.isEmpty() && !city.isEmpty()){
                val addData = AddData(id, lat, lon, name, opt)
                database.push().setValue(addData)
                Toast.makeText(this, R.string.check_is_successfully, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, R.string.check_add, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onButtonClick(view: View) {
        val intent = Intent(this, TrainsActivity::class.java)
        startActivity(intent)
    }
}
