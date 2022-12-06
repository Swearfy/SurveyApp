package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question

class SurveyEditPanelandDataUser : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var surveyid = 0
    var transferUserId= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data_user)

        val chosensurveyId = intent.getIntExtra("title",0)
        val userId = intent.getIntExtra("userId",0)

        surveyid = chosensurveyId
        transferUserId = userId

        val survey = dbHelper.getSurveyById(chosensurveyId)

        val title = findViewById<TextView>(R.id.text_editTitle2)
        val startDate = findViewById<TextView>(R.id.text_editStartDate2)
        val endDate = findViewById<TextView>(R.id.text_editEndDate2)


        title.text = survey.surveyTitle
        startDate.text = survey.surveyStartDate
        endDate.text = survey.surveyEndDate
    }

    fun answer(view: View){
        val intent = Intent(this,UserAnswerPanel::class.java)
        intent.putExtra("surveyid",surveyid)
        intent.putExtra("userId",transferUserId)
        startActivity(intent)
    }


    fun goBack(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }
}