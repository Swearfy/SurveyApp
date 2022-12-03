package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper

class MainActivity : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun login(view :View){

        val intent = Intent(this,AdminPanel::class.java)
        val intent2 = Intent(this,UserPanel::class.java)

        var userName = findViewById<EditText>(R.id.text_username).text.toString()
        var passWord = findViewById<EditText>(R.id.text_Password).text.toString()

        val actualUsername = dbHelper.getUser(userName)

        if (userName.isBlank() && passWord.isBlank()){
            Toast.makeText(this,"Please Fill in the boxes above", Toast.LENGTH_LONG).show()
        }else if (userName.isBlank() ){
            Toast.makeText(this,"Please Fill in username", Toast.LENGTH_LONG).show()
        }else if (passWord.isBlank() ){
            Toast.makeText(this,"Please Fill in password", Toast.LENGTH_LONG).show()
        }else if(userName == actualUsername.userName && passWord == actualUsername.passWord && actualUsername.isAdmin == 1){
            startActivity(intent)
        }else if(userName == actualUsername.userName && passWord == actualUsername.passWord && actualUsername.isAdmin == 0){
            startActivity(intent2)
        }else if(userName == actualUsername.userName && passWord != actualUsername.passWord){
            Toast.makeText(this,"password is wrong", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"User does not exist ", Toast.LENGTH_LONG).show()
        }
    }


    fun register(view: View){
        val intent = Intent(this,RegisterPanel::class.java)
        startActivity(intent)
    }
}