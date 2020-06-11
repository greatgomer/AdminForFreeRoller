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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val sentData = findViewById<Button>(R.id.buttonAddCity)
        val editCityNameEn = findViewById<EditText>(R.id.editTextCityNameEn)
        val editCityNameRu = findViewById<EditText>(R.id.editTextCityNameRu)

        sentData.setOnClickListener {
            val nameEn = editCityNameEn.text.toString()
            val nameRu = editCityNameRu.text.toString()
            database = AppDatabase.getDatabase()!!.getReference(cities)
            if(nameEn.isNotEmpty() && nameRu.isNotEmpty()){
                val addNames = AddCity(nameEn, nameRu)
                database.push().setValue(addNames)
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