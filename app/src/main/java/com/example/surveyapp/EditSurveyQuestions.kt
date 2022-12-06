package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question
import com.example.surveyapp.Model.Survey

class EditSurveyQuestions : AppCompatActivity() {
    val dbHelper = DataBaseHelper(this)
    var transferId2 = 0
    val newArray = ArrayList<Question>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_survey_questions)
        val transferId = intent.getIntExtra("id",0)

        transferId2 = transferId
        val questions = dbHelper.getAllQuestionsBySurveyId(transferId2)


        for (question in questions){
            newArray.add(question)
        }

        findViewById<EditText>(R.id.text_question1v2).setText(newArray[0].questionText)
        findViewById<EditText>(R.id.text_question2v2).setText(newArray[1].questionText)
        findViewById<EditText>(R.id.text_question3v2).setText(newArray[2].questionText)
        findViewById<EditText>(R.id.text_question4v2).setText(newArray[3].questionText)
        findViewById<EditText>(R.id.text_question5v2).setText(newArray[4].questionText)
        findViewById<EditText>(R.id.text_question6v2).setText(newArray[5].questionText)
        findViewById<EditText>(R.id.text_question7v2).setText(newArray[6].questionText)
        findViewById<EditText>(R.id.text_question8v2).setText(newArray[7].questionText)
        findViewById<EditText>(R.id.text_question9v2).setText(newArray[8].questionText)
        findViewById<EditText>(R.id.text_question10v2).setText(newArray[9].questionText)

    }

    fun publish2(view: View){


        val title = intent.getStringExtra("title").toString()
        val startDate = intent.getStringExtra("startDate").toString()
        val endDate = intent.getStringExtra("endDate").toString()


        val question1 = findViewById<EditText>(R.id.text_question1v2).text.toString()
        val question2 = findViewById<EditText>(R.id.text_question2v2).text.toString()
        val question3 = findViewById<EditText>(R.id.text_question3v2).text.toString()
        val question4 = findViewById<EditText>(R.id.text_question4v2).text.toString()
        val question5 = findViewById<EditText>(R.id.text_question5v2).text.toString()
        val question6 = findViewById<EditText>(R.id.text_question6v2).text.toString()
        val question7 = findViewById<EditText>(R.id.text_question7v2).text.toString()
        val question8 = findViewById<EditText>(R.id.text_question8v2).text.toString()
        val question9 = findViewById<EditText>(R.id.text_question9v2).text.toString()
        val question10 = findViewById<EditText>(R.id.text_question10v2).text.toString()

        if (question1.isBlank() ||
            question2.isBlank() ||
            question3.isBlank() ||
            question4.isBlank() ||
            question5.isBlank() ||
            question6.isBlank() ||
            question7.isBlank() ||
            question8.isBlank() ||
            question9.isBlank() ||
            question10.isBlank()){
            Toast.makeText(this,"Please fill in all the questions", Toast.LENGTH_SHORT).show()
            return
        }else{

            val survey = Survey(transferId2,title, startDate,endDate)


                if (dbHelper.updateSurvey(survey)) {
                    Toast.makeText(this, "Survey updated", Toast.LENGTH_SHORT).show()


                    val question1 = Question(newArray[0].questionId,question1,transferId2)
                    val question2 = Question(newArray[1].questionId,question2,transferId2)
                    val question3 = Question(newArray[2].questionId,question3,transferId2)
                    val question4 = Question(newArray[3].questionId,question4,transferId2)
                    val question5 = Question(newArray[4].questionId,question5,transferId2)
                    val question6 = Question(newArray[5].questionId,question6,transferId2)
                    val question7 = Question(newArray[6].questionId,question7,transferId2)
                    val question8 = Question(newArray[7].questionId,question8,transferId2)
                    val question9 = Question(newArray[8].questionId,question9,transferId2)
                    val question10 = Question(newArray[9].questionId,question10,transferId2)

                    dbHelper.updateQuetion(question1)
                    dbHelper.updateQuetion(question2)
                    dbHelper.updateQuetion(question3)
                    dbHelper.updateQuetion(question4)
                    dbHelper.updateQuetion(question5)
                    dbHelper.updateQuetion(question6)
                    dbHelper.updateQuetion(question7)
                    dbHelper.updateQuetion(question8)
                    dbHelper.updateQuetion(question9)
                    dbHelper.updateQuetion(question10)
                    val intent = Intent(this,AdminPanel::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }

        }

    }
    fun goBack2(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }
}