package com.example.headsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var tapoo1:EditText
    lateinit var tapoo2:EditText
    lateinit var tapoo3:EditText
    lateinit var updel:EditText
    lateinit var save:Button
    lateinit var upddelbtn:Button
    lateinit var tv:TextView
    lateinit var al :ArrayList<Clibrites>
    var myName = ""
    var myTapo1 = ""
    var myTapoo2 = ""
    var myTapoo3 = ""
    var MyText = ""
    lateinit var dbhlpr:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        name = findViewById(R.id.editTextTextPersonName)
        tapoo1 = findViewById(R.id.editTextTextPersonName2)
        tapoo2 = findViewById(R.id.editTextTextPersonName3)
        tapoo3 = findViewById(R.id.editTextTextPersonName4)
        updel = findViewById(R.id.updel)
        save = findViewById(R.id.button3)
        upddelbtn = findViewById(R.id.upddelbtn)
        tv = findViewById(R.id.Clibritestv)
         dbhlpr = DBHelper(this)
        al = arrayListOf()
        tvtxt()





        upddelbtn.setOnClickListener{
            var myclibrites = dbhlpr.retriveData()
            var nametoUpdate = updel.text.toString()
            for (i in myclibrites){
                if (i.name == nametoUpdate){
                    startActivity(Intent(this,MainActivity3::class.java)
                        .putExtra("myID",i.id)
                        .putExtra("name","${i.name}")
                        .putExtra("tapoo1","${i.tapoo1}")
                        .putExtra("tapoo2","${i.tapoo2}")
                        .putExtra("tapoo3","${i.tapoo3}"))

                    Log.d("ppp","$i")
                }
            }


        }




        save.setOnClickListener {
            myName = name.text.toString()
            myTapo1 = tapoo1.text.toString()
            myTapoo2 = tapoo2.text.toString()
            myTapoo3 = tapoo3.text.toString()
            MyText += "$myName $myTapo1 $myTapoo2 $myTapoo3 \n"
            tv.text = MyText
            var status =  dbhlpr.saveddata(myName,myTapo1,myTapoo2,myTapoo3)
            Toast.makeText(this,"data Saved successfully +$status", Toast.LENGTH_SHORT).show()


        }


    }
    fun tvtxt(){
        var cil = dbhlpr.retriveData()

        for (i in cil.indices){
            MyText += "${cil[i].name} ${cil[i].tapoo1} ${cil[i].tapoo2} ${cil[i].tapoo3}\n"
            tv.text = MyText
        }

    }
}