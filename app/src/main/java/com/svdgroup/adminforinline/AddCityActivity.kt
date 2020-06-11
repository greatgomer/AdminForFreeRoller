package com.svdgroup.adminforinline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference

class AddCityActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var cities = "Cities"
    var city = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val sentData = findViewById<Button>(R.id.buttonAddCity)
        val editCity = findViewById<EditText>(R.id.editTextCityName)

        sentData.setOnClickListener {
            val city = editCity.text.toString()
            database = AppDatabase.getDatabase()!!.getReference(cities)
            database.push().setValue(city)
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