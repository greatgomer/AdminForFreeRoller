package com.svdgroup.adminforinline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_trains.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var places_name = "Places"
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var spinner: Spinner? = null
        val sentData = findViewById<Button>(R.id.buttonSetData)
        val editLat = findViewById<EditText>(R.id.editText_lat)
        val editLon = findViewById<EditText>(R.id.editText_lon)
        val editName = findViewById<EditText>(R.id.editText_name)
        val editOpt = findViewById<EditText>(R.id.editText_opt)
        val cities = resources.getStringArray(R.array.cities)

        spinner = this.spinner_city

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = aa


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                city = cities[position]
            }
        }

        database = FirebaseDatabase.getInstance().getReference(places_name)

        sentData.setOnClickListener {
            val id = database.key.toString()
            val lat = editLat.text.toString()
            val lon = editLon.text.toString()
            val name = editName.text.toString()
            val opt = editOpt.text.toString()
            database = FirebaseDatabase.getInstance().getReference(places_name).child(city)
            if(lat.isNotEmpty() && lon.isNotEmpty() && name.isNotEmpty() && opt.isNotEmpty() && city.isNotEmpty()){
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