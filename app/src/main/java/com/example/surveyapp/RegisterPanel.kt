package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch

class RegisterPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_panel)

        val toggle = findViewById<Switch>(R.id.btn_SwitchLogin)

        toggle.text = "User"
        findViewById<EditText>(R.id.accesCode).visibility = View.INVISIBLE

        toggle.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                toggle.text = "Admin"
                findViewById<EditText>(R.id.accesCode).visibility = View.VISIBLE
            } else {
                toggle.text = "User"
                findViewById<EditText>(R.id.accesCode).visibility = View.INVISIBLE

            }
        }

    }


    fun goBackToLoging(view: View){
        val intent = Intent(this,MainActivity::class.java)

        startActivity(intent)
    }
}