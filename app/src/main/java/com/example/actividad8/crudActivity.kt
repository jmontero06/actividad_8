package com.example.actividad8

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class crudActivity : AppCompatActivity() {

    lateinit var studensDBHelper:sqlHelper

    fun getData(DB: SQLiteOpenHelper, sqlCondition:String,txvId:TextView,edtName:EditText,edtCount:EditText) {
        val db:SQLiteDatabase=DB.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM estudiantes WHERE nombre='${sqlCondition}'",null)
        if (cursor.moveToFirst()) {
            do {
                txvId.text=cursor.getInt(0).toString()
                edtName.setText(cursor.getString(1))
                edtCount.setText(cursor.getString(2))
            } while (cursor.moveToNext())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)

        //enlace de los elementos visuales
        val txvId:TextView=findViewById(R.id.txvId)
        val edtName:EditText=findViewById(R.id.edtNameCrud)
        val edtCount:EditText=findViewById(R.id.edtCuentaCrud)
        val btnDelete:Button=findViewById(R.id.btnDelete)

        //datos del intent
        val resIntent:Intent=intent
        val nombre=resIntent.getStringExtra("nombre").toString()

        studensDBHelper= sqlHelper(this)
        getData(studensDBHelper,nombre,txvId,edtName,edtCount)

        btnDelete.setOnClickListener {
            val res=studensDBHelper.deleteData(txvId.text.toString().toInt())
            if (res>0) {
                val intent:Intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "datos eleminados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "ocurrio un error", Toast.LENGTH_LONG).show()
            }
        }
    }
}