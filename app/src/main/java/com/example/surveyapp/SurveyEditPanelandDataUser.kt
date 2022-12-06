package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.surveyapp.Model.DataBaseHelper

class SurveyEditPanelandDataUser : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var surveyid = 0
    var transferUserId= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data_user)

        surveyid = intent.getIntExtra("title",0)
        transferUserId = intent.getIntExtra("userId",0)

        val survey = dbHelper.getSurveyById(surveyid)

        findViewById<TextView>(R.id.text_editTitle2).text = survey.surveyTitle
        findViewById<TextView>(R.id.text_editStartDate2).text = survey.surveyStartDate
        findViewById<TextView>(R.id.text_editEndDate2).text = survey.surveyEndDate
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