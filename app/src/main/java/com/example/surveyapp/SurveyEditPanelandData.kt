package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.surveyapp.Model.DataBaseHelper

class SurveyEditPanelandData : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data)

        val chosensurveyId = intent.getIntExtra("title",0)

        id = chosensurveyId

        val survey = dbHelper.getSurveyById(chosensurveyId)

        val title = findViewById<TextView>(R.id.text_editTitle)
        val startDate = findViewById<TextView>(R.id.text_editStartDate)
        val endDate = findViewById<TextView>(R.id.text_editEndDate)


        title.text = survey.surveyTitle
        startDate.text = survey.surveyStartDate
        endDate.text = survey.surveyEndDate
    }

    fun edit(view: View){
        val intent = Intent(this,EditSurveyTitlePanel::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    fun goBack(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }
}