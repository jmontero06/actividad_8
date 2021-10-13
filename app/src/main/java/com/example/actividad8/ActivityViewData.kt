package com.example.actividad8

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ActivityViewData : AppCompatActivity() {

    lateinit var studensDBHelper:sqlHelper

    fun getData(txvView:TextView, DB:SQLiteOpenHelper){
        txvView.text=""
        var db:SQLiteDatabase=DB.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM estudiantes",null)
        if (cursor.moveToFirst()) {
            do {
                txvView.append(cursor.getInt(0).toString()+" : ")
                txvView.append(cursor.getString(1).toString()+", ")
                txvView.append(cursor.getString(2).toString()+"\n")
            }while (cursor.moveToNext())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        studensDBHelper= sqlHelper(this)

        //enlce de los elementos visuales
        val txtView:TextView=findViewById(R.id.txvViewData)
        getData(txtView,studensDBHelper)
    }
}