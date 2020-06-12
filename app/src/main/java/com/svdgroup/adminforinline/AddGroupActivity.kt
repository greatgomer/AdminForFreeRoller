package com.svdgroup.adminforinline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_add_group.*

class AddGroupActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private val citiesTitle = "Cities"
    private val linksTitle = "Links"
    var cityId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var spinner: Spinner? = null
        val editGroup = findViewById<EditText>(R.id.editText_group)

        val citiesList = CitiesList()
        citiesList.getNames().observe(this, Observer { cities ->
            val cityNames = cities.map { it.second }
            spinner = this.spinner_city_chouse

            val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityNames)
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner!!.adapter = aa

            spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    cityId = cities[position].first
                }
            }
        })

                    database = AppDatabase.getDatabase()!!.getReference(citiesTitle)

            buttonSentData.setOnClickListener {
                val group = editGroup.text.toString()
                database = AppDatabase.getDatabase()!!.getReference(citiesTitle).child(cityId).child(linksTitle)
                if(group.isNotEmpty()){
                    database.push().setValue(group)
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