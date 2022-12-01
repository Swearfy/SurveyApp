package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view :View){

        val intent = Intent(this,AdminPanel::class.java)
        var userName = findViewById<EditText>(R.id.text_username).text.toString()
        var passWord = findViewById<EditText>(R.id.text_Password).text.toString()

        if (userName.isBlank() && passWord.isBlank()){
            Toast.makeText(this,"Please Fill in the boxes above", Toast.LENGTH_LONG).show()
        }else if (userName.isBlank() ){
            Toast.makeText(this,"Please Fill in username", Toast.LENGTH_LONG).show()
        }else if (passWord.isBlank() ){
            Toast.makeText(this,"Please Fill in password", Toast.LENGTH_LONG).show()
        }else if(userName == "yaes" && passWord=="yaes"){
            startActivity(intent)
        }
    }
}