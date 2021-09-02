package com.example.getpostandpostcomment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.getpostandpostcomment.mvc.ui.mvc.ListOfUsers
import com.example.getpostandpostcomment.mvvm.ui.mvvm.MvvmListOfUsers

class MainActivity : AppCompatActivity() {
    private lateinit var nextButtonMvc: Button
    private lateinit var nextButtonMvvm: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nextButtonMvc = findViewById(R.id.mvc)
        nextButtonMvvm = findViewById(R.id.mvvm)
        nextButtonMvc.setOnClickListener {
            val intent = Intent(this, ListOfUsers::class.java)
            startActivity(intent)
        }
        nextButtonMvvm.setOnClickListener {
            val intent = Intent(this, MvvmListOfUsers::class.java)
            startActivity(intent)
        }

    }
}