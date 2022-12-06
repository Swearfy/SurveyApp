package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question
import com.example.surveyapp.Model.Survey

class SurveyEditPanelandData : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var id = 0

    val newArray = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data)

        val chosensurveyId = intent.getIntExtra("title",0)

        id = chosensurveyId

        val questions = dbHelper.getAllQuestionsBySurveyId(id)


        for (question in questions){
            newArray.add(question)
        }

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


    fun delete(view: View){

        if (dbHelper.deleteSurvey(id)) {
            Toast.makeText(this, "Survey deleted", Toast.LENGTH_SHORT).show()

            dbHelper.deleteQuestion(newArray[0].questionId)
            dbHelper.deleteQuestion(newArray[1].questionId)
            dbHelper.deleteQuestion(newArray[2].questionId)
            dbHelper.deleteQuestion(newArray[3].questionId)
            dbHelper.deleteQuestion(newArray[4].questionId)
            dbHelper.deleteQuestion(newArray[5].questionId)
            dbHelper.deleteQuestion(newArray[6].questionId)
            dbHelper.deleteQuestion(newArray[7].questionId)
            dbHelper.deleteQuestion(newArray[8].questionId)
            dbHelper.deleteQuestion(newArray[9].questionId)
            val intent = Intent(this,AdminPanel::class.java)
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