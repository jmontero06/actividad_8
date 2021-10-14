package com.example.actividad8

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqlHelper(context: Context):SQLiteOpenHelper(context,"escuela.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val creationOrder="CREATE TABLE estudiantes("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT,"+
                "cuenta TEXT);"
        db!!.execSQL(creationOrder)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val orden="DROP TABLE IF EXISTS estudiantes"
        db!!.execSQL(orden)
        onCreate(db)
    }
    fun addData(nobre:String,cuenta:String){
        val datos=ContentValues()
        datos.put("nombre",nobre)
        datos.put("cuenta",cuenta)

        val db=this.writableDatabase
        db.insert("estudiantes",null,datos)
        db.close()
    }
}