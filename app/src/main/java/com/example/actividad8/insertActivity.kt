package com.example.actividad8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class insertActivity : AppCompatActivity() {

    lateinit var studensDBHelper:sqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        fun emptyCamp(name:EditText, cta:EditText): Boolean {
            return name.text.isBlank() || cta.text.isBlank()
        }

        //enlace de los elemntos visuales
        val edtName:EditText=findViewById(R.id.edtName)
        val edtCuenta:EditText=findViewById(R.id.edtNumCta)
        val btnInsert:Button=findViewById(R.id.btnInsertData)

        studensDBHelper=sqlHelper(this)

        btnInsert.setOnClickListener {
            if (emptyCamp(edtName,edtCuenta)) {
                Toast.makeText(this, "algun campo esta vacio", Toast.LENGTH_LONG).show()
            }else {
                studensDBHelper.addData(edtName.text.toString(),edtCuenta.text.toString())

                Toast.makeText(this, "datos incertados correctamente", Toast.LENGTH_SHORT).show()

                edtName.text.clear()
                edtCuenta.text.clear()
            }
        }
    }
}