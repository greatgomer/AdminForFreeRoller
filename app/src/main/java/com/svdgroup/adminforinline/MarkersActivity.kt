package com.svdgroup.adminforinline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_markers.*

class MarkersActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var places_name = "Places"
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_markers)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var spinner: Spinner? = null
        val sentData = findViewById<Button>(R.id.buttonSetData)
        val editLat = findViewById<EditText>(R.id.editText_group)
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

        database = AppDatabase.getDatabase()!!.getReference(places_name)

        sentData.setOnClickListener {
            val id = database.key.toString()
            val lat = editLat.text.toString()
            val lon = editLon.text.toString()
            val name = editName.text.toString()
            val opt = editOpt.text.toString()
            database = AppDatabase.getDatabase()!!.getReference(places_name).child(city)
            if(lat.isNotEmpty() && lon.isNotEmpty() && name.isNotEmpty() && opt.isNotEmpty() && city.isNotEmpty()){
                val addData = AddData(id, lat, lon, name, opt)
                database.push().setValue(addData)
                Toast.makeText(this, R.string.check_is_successfully, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, R.string.check_add, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // app icon in action bar clicked; go home
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}