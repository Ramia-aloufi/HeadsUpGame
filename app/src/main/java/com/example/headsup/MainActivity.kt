package com.example.headsup

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.view.Surface
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import android.content.Intent




class MainActivity : AppCompatActivity() {

    lateinit var portratstart: RelativeLayout
    lateinit var portrat: RelativeLayout
    lateinit var land: RelativeLayout
    lateinit var celebrities: ArrayList<JSONObject>
    lateinit var title: TextView
    lateinit var someOf: TextView
    private lateinit var tim: TextView
    private lateinit var btn: Button
    var ii = 0
    var starta = true
    var startb = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btn.setOnClickListener {
            start()
        }

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        rotation()

    }

    fun init() {
        portratstart = findViewById(R.id.portratstart)
        portrat = findViewById(R.id.portrat)
        land = findViewById(R.id.land)
        title = findViewById(R.id.title)
        someOf = findViewById(R.id.someOf)
        celebrities = arrayListOf()
        tim = findViewById(R.id.time)
        btn = findViewById(R.id.button)
    }

    fun start() {
        time()
        portratstart.isVisible = false
        portrat.isVisible = true
    }

    fun rotation() {
        val rotation = windowManager.defaultDisplay.rotation
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            firstactivity(starta)
            land.visibility = View.GONE
            Log.d("starta","$starta")
            Log.d("startb","$startb")
        } else {
            requestAPIData()
            ii++
            Log.d("starta","$starta")
            Log.d("startb","$startb")

            portrat.visibility = View.GONE
            portratstart.visibility = View.GONE
            secondactivity(startb)

        }
    }

    fun firstactivity(status: Boolean) {
        if(status == true) {
            portrat.visibility = View.GONE
            portratstart.visibility = View.VISIBLE
        } else {
            portrat.visibility = View.VISIBLE
            portratstart.visibility = View.GONE
        }

    }

    fun secondactivity(was: Boolean) {
        if(was) {
            land.visibility = View.VISIBLE
        } else {
            land.visibility = View.GONE
        }
    }

    fun time() {
        starta = !starta
        startb = !startb
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tim.text = "Time: ${millisUntilFinished / 1000}"

            }

            override fun onFinish() {
                starta = !starta
                startb = !startb
                tim.text = "Time: "
            }
        }.start()

    }

    fun requestAPIData() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                checkApiUrl()
            }.await()
            Log.d("qqqq", "$data")
            if (data.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    apiDataToJsonArray(data)
                }
            } else {

            }
        }
    }

    fun checkApiUrl(): String {
        var response = ""
        try {
            response = URL("https://dojo-recipes.herokuapp.com/celebrities/")
                .readText(Charsets.UTF_8)
        } catch (e: Exception) {
            println("Error: $e")

        }
        println(response)
        Log.d("response", response.toString())
        return response
    }

    suspend fun apiDataToJsonArray(result: String) {
        withContext(Dispatchers.Main) {
            celebrities.clear()
            val jsonArray = JSONArray(result)
            println(jsonArray)
            Log.d("jsonArray", jsonArray.toString())
            for (i in 0 until jsonArray.length()) {
                celebrities.add(jsonArray.getJSONObject(i))
            }
            jsonarrayToUI()


        }
    }

    fun jsonarrayToUI() {
        title.text = celebrities[ii].getString("name")
        var aa = celebrities[ii].getString("taboo1")
        var bb = celebrities[ii].getString("taboo2")
        var qq = celebrities[ii].getString("taboo3")
        someOf.text = "$aa\n$bb\n$qq"
        Log.d("titl", title.toString())
        Log.d("titl", "$aa\n$bb\n$qq")
    }


}