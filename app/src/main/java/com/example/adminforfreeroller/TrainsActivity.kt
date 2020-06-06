package com.example.adminforfreeroller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        spinner = this.spinner_style

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, style_array)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)


        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                style = style_array[position]
            }
        }

        database = FirebaseDatabase.getInstance().getReference(trains_name)

        sentTrain.setOnClickListener {
            var id = database.getKey().toString()
            var name = editName.text.toString()
            var picture = editPicture.text.toString()
            var complexity = editComplexity.text.toString()
            var description = editDescription.text.toString()
            var video = editVideo.text.toString()
            database = FirebaseDatabase.getInstance().getReference(trains_name).child(style)
            if(!style.isEmpty() && !name.isEmpty() && !picture.isEmpty() && !complexity.isEmpty() && !description.isEmpty() && !video.isEmpty()){
                val addTrain = AddTrains(id, name, picture, complexity, description, video)
                database.push().setValue(addTrain)
                Toast.makeText(this, R.string.check_is_successfully, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, R.string.check_add, Toast.LENGTH_SHORT).show()
            }
        }

        }

    fun onButtonClickMarkers(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
