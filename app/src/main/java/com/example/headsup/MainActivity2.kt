package com.example.headsup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var tapoo1:EditText
    lateinit var tapoo2:EditText
    lateinit var tapoo3:EditText
    lateinit var save:Button
    var myName = ""
    var myTapo1 = ""
    var myTapoo2 = ""
    var myTapoo3 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        name = findViewById(R.id.editTextTextPersonName)
        tapoo1 = findViewById(R.id.editTextTextPersonName2)
        tapoo2 = findViewById(R.id.editTextTextPersonName3)
        tapoo3 = findViewById(R.id.editTextTextPersonName4)
        save = findViewById(R.id.button3)

        save.setOnClickListener {
            myName = name.text.toString()
            myTapo1 = tapoo1.text.toString()
            myTapoo2 = tapoo2.text.toString()
            myTapoo3 = tapoo3.text.toString()

            var dbhlpr = DBHelper(this)
            var status =  dbhlpr.saveddata(myName,myTapo1,myTapoo2,myTapoo3)
            Toast.makeText(this,"data Saved successfully +$status", Toast.LENGTH_SHORT).show()

        }
    }
}