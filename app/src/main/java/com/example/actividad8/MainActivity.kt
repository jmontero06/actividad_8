package com.example.actividad8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //enlasamos los elementos visuales
        val btnInsert:Button=findViewById(R.id.btnInsert)
        val btnView:Button=findViewById(R.id.btnview)

        btnInsert.setOnClickListener {
            val intent:Intent= Intent(this,insertActivity::class.java)
            startActivity(intent)
        }
        btnView.setOnClickListener {
            val intent:Intent=Intent(this,ActivityViewData::class.java)
            startActivity(intent)
        }
    }
}