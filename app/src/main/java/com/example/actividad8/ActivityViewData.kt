package com.example.actividad8

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class ActivityViewData : AppCompatActivity() {

    lateinit var studensDBHelper:sqlHelper

    fun printData(lista:MutableList<String>,lviData:ListView){
        val adapter:ArrayAdapter<*>
        adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,lista)
        lviData.adapter=adapter
    }

    fun getData(DB:SQLiteOpenHelper): MutableList<String> {
        val db:SQLiteDatabase=DB.readableDatabase
        val lista= mutableListOf<String>()
        val cursor=db.rawQuery("SELECT nombre FROM estudiantes",null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0))
            }while (cursor.moveToNext())
        }
        return lista
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        studensDBHelper= sqlHelper(this)

        //enlce de los elementos visuales
        val lviData:ListView=findViewById(R.id.lviData)
        printData(getData(studensDBHelper),lviData)

        lviData.setOnItemClickListener(){
            parent,view,position,id->
            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
        }
    }
}