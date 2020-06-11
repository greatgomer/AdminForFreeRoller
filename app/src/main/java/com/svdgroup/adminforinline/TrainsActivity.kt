package com.svdgroup.adminforinline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_trains.*

class TrainsActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    var style_array = arrayOf("SLALOM", "SLIDE", "JUMP", "AGGRESSIV")
    private var trains_name = "Trains"
    var style = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trains)

        var spinner:Spinner? = null
        val editName = findViewById<EditText>(R.id.editText_trains_name)
        val editPicture = findViewById<EditText>(R.id.editText_picture_link)
        val editComplexity = findViewById<EditText>(R.id.editText_complexity)
        val editDescription = findViewById<EditText>(R.id.editText_train_description)
        val editVideo = findViewById<EditText>(R.id.editText_video_link)
        val sentTrain = findViewById<Button>(R.id.button_add_train)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        spinner = this.spinner_style

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, style_array)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = aa


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                style = style_array[position]
            }
        }

        database = AppDatabase.getDatabase()!!.getReference(trains_name)

        sentTrain.setOnClickListener {
            val id = database.key.toString()
            val name = editName.text.toString()
            val picture = editPicture.text.toString()
            val complexity = editComplexity.text.toString()
            val description = editDescription.text.toString()
            val video = editVideo.text.toString()
            database = AppDatabase.getDatabase()!!.getReference(trains_name).child(style)
            if(style.isNotEmpty() && name.isNotEmpty() && picture.isNotEmpty() && complexity.isNotEmpty() && description.isNotEmpty() && video.isNotEmpty()){
                val addTrain = AddTrains(id, name, picture, complexity, description, video)
                database.push().setValue(addTrain)
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