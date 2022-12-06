package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question

class SurveyEditPanelandData : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    val newArray = ArrayList<Question>()
    var chosensurveyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data)

        chosensurveyId = intent.getIntExtra("surveyId",0)

        val questions = dbHelper.getAllQuestionsBySurveyId(chosensurveyId)
        val survey = dbHelper.getSurveyById(chosensurveyId)

        for (question in questions){
            newArray.add(question)
        }

        findViewById<TextView>(R.id.text_editTitle).text = survey.surveyTitle
        findViewById<TextView>(R.id.text_editStartDate).text = survey.surveyStartDate
        findViewById<TextView>(R.id.text_editEndDate).text = survey.surveyEndDate
    }

    fun edit(view: View){
        val intent = Intent(this,EditSurveyTitlePanel::class.java)
        intent.putExtra("surveyId",chosensurveyId)
        startActivity(intent)
    }


    fun delete(view: View){

        if (dbHelper.deleteSurvey(chosensurveyId)) {
            val intent = Intent(this,AdminPanel::class.java)

            for (i in 0 until 10){
                dbHelper.deleteQuestion(newArray[i].questionId)
            }

            Toast.makeText(this, "Survey deleted", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    fun goBack(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }
}