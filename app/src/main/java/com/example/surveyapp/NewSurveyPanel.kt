package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper

class NewSurveyPanel : AppCompatActivity() {

    val dbHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_survey_panel)
    }


    fun next(view: View){

        val title = findViewById<EditText>(R.id.text_title).text.toString()
        val startDate = findViewById<EditText>(R.id.text_startDate).text.toString()
        val endDate = findViewById<EditText>(R.id.text_endDate).text.toString()

        val checkTitle = dbHelper.getSurvey(title)

        if (title.isBlank()){
            Toast.makeText(this,"Please enter a title",Toast.LENGTH_SHORT).show()
            return
        }
        if (startDate.isBlank()){
            Toast.makeText(this,"Please enter a starting date",Toast.LENGTH_SHORT).show()
            return
        }
        if (checkTitle.toString() == title){
            Toast.makeText(this,"Survey already exists with this title please change it", Toast.LENGTH_SHORT).show()
            return
        }
        else
        {
            val intent = Intent(this, QuestionPanel::class.java)

            intent.putExtra("title",title)
            intent.putExtra("startDate",startDate)
            intent.putExtra("endDate",endDate)

            startActivity(intent)
        }
    }

    fun goBack(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }


}