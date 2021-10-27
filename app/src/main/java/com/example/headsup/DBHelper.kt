package com.example.headsup

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"HeadsUp.db",null,1) {
   var sdb:SQLiteDatabase = writableDatabase
    var defaultarray = arrayListOf<Clibrites>(
        Clibrites("messi","player","football","manchester"),
        Clibrites("ket","princess","william","diana"),
        Clibrites("korona","viarus","covid-19","stay-home"),
        Clibrites("saudi arabia","2030","twaiq","riyadh"),
        Clibrites("coding dojo","learning","coding","development"),
        Clibrites("messi","player","football","manchester"),
        Clibrites("ket","princess","william","diana"),
        Clibrites("korona","viarus","covid-19","stay-home"),
        Clibrites("saudi arabia","2030","twaiq","riyadh"),
        Clibrites("coding dojo","learning","coding","development"),


    )
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL("create table headupdata (name text ,tapoo1 text,tapoo2 text,tapoo3 text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun saveddata(n1:String,n2:String,n3:String,n4:String): Long {
        val cv = ContentValues()
        cv.put("name",n1)
        cv.put("tapoo1",n2)
        cv.put("tapoo2",n2)
        cv.put("tapoo3",n3)

        var status = sdb.insert("headupdata",null,cv)
        return status

    }

    @SuppressLint("Range")
    fun retriveData():ArrayList<Clibrites>{
        var al = arrayListOf<Clibrites>()
        var c : Cursor = sdb.query("headupdata",null,null, null,null,null,null)
      if(c.count == 0){
          al = defaultarray
      }else{
        if (c.moveToFirst()) {
            do {
                var name =   c.getString(c.getColumnIndex("name"))
                var tapoo1 = c.getString(c.getColumnIndex("tapoo1"))
                var tapoo2 = c.getString(c.getColumnIndex("tapoo2"))
                var tapoo3 = c.getString(c.getColumnIndex("tapoo3"))
                al.add(Clibrites(name, tapoo1, tapoo2, tapoo3))
            } while (c.moveToNext());
        }}
        return al
    }
}