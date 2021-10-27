package com.example.headsup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var tapoo1:EditText
    private lateinit var tapoo2:EditText
   private lateinit var tapoo3:EditText
    lateinit var db:DBHelper
    var id = 0


    lateinit var update:Button
   lateinit var delete:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        init()

        update.setOnClickListener {
            var tt = db.update(id,name.text.toString(),tapoo1.text.toString(),tapoo2.text.toString(),tapoo3.text.toString())
            Toast.makeText(this,"Updated $tt",Toast.LENGTH_SHORT).show()
            Log.d("","$id,${name.text.toString()},${tapoo1.text.toString()},${tapoo2.text.toString()},${tapoo3.text.toString()}")
            clear()

        }

        delete.setOnClickListener {
            var tyty = db.delete(id)
            Toast.makeText(this,"Deleted $tyty",Toast.LENGTH_SHORT).show()
            clear()

        }
    }

    fun init(){
        name = findViewById(R.id.editname)
        tapoo1 = findViewById(R.id.editapoo1)
        tapoo2 = findViewById(R.id.edittapoo2)
        tapoo3 = findViewById(R.id.edittapoo3)
        update = findViewById(R.id.update)
        delete = findViewById(R.id.delete)
        db = DBHelper(this)


        id = intent.getIntExtra("myID",0)
        name.setText(intent.getStringExtra("name"))
        tapoo1.setText(intent.getStringExtra("tapoo1"))
        tapoo2.setText(intent.getStringExtra("tapoo2"))
        tapoo3.setText(intent.getStringExtra("tapoo3"))
    }
    fun clear(){
        name.text.clear()
        tapoo1.text.clear()
        tapoo2.text.clear()
        tapoo3.text.clear()
    }

}