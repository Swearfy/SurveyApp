package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper

class EditSurveyTitlePanel : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var transferId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_survey_title_panel)

        val id = intent.getIntExtra("surveyId",0)

        val chosenIdsurvey = dbHelper.getSurveyById(id)

        transferId = chosenIdsurvey.surveyId
        findViewById<EditText>(R.id.text_title2).setText(chosenIdsurvey.surveyTitle)
        findViewById<EditText>(R.id.text_startDate2).setText(chosenIdsurvey.surveyStartDate)
        findViewById<EditText>(R.id.text_endDate2).setText(chosenIdsurvey.surveyEndDate)
    }

    fun next2(view: View){
        val intent = Intent(this, EditSurveyQuestions::class.java)

        val title = findViewById<EditText>(R.id.text_title2).text.toString()
        val startDate = findViewById<EditText>(R.id.text_startDate2).text.toString()
        val endDate = findViewById<EditText>(R.id.text_endDate2).text.toString()

        if (title.isBlank()){
            Toast.makeText(this,"Please enter a title", Toast.LENGTH_SHORT).show()
            return
        }
        if (startDate.isBlank()){
            Toast.makeText(this,"Please enter a starting date", Toast.LENGTH_SHORT).show()
            return
        }
            intent.putExtra("surveyId",transferId)
            intent.putExtra("title",title)
            intent.putExtra("startDate",startDate)
            intent.putExtra("endDate",endDate)

            startActivity(intent)
    }

    fun goBack2(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }
}