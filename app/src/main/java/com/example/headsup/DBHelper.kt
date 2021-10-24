package com.example.headsup

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"HeadsUp.db",null,1) {
   var sdb:SQLiteDatabase = writableDatabase
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
}