package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question
import com.example.surveyapp.Model.Survey

class QuestionPanel : AppCompatActivity() {

    val dbHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_panel)
    }

    fun publish(view: View){

        val title = intent.getStringExtra("title").toString()
        val startDate = intent.getStringExtra("startDate").toString()
        val endDate = intent.getStringExtra("endDate").toString()


        val question1 = findViewById<EditText>(R.id.text_question).text.toString()
        val question2 = findViewById<EditText>(R.id.text_question2).text.toString()
        val question3 = findViewById<EditText>(R.id.text_question3).text.toString()
        val question4 = findViewById<EditText>(R.id.text_question4).text.toString()
        val question5 = findViewById<EditText>(R.id.text_question5).text.toString()
        val question6 = findViewById<EditText>(R.id.text_question6).text.toString()
        val question7 = findViewById<EditText>(R.id.text_question7).text.toString()
        val question8 = findViewById<EditText>(R.id.text_question8).text.toString()
        val question9 = findViewById<EditText>(R.id.text_question9).text.toString()
        val question10 = findViewById<EditText>(R.id.text_question10).text.toString()


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

            val survey = Survey(0,title, startDate,endDate)


            if (dbHelper.addSurvey(survey)) {
                Toast.makeText(this, "Survey created", Toast.LENGTH_SHORT).show()


                val surveyFinder = dbHelper.getSurvey(survey.surveyTitle)
                val surveyId = surveyFinder.surveyId

                val question1 = Question(0,question1,surveyId)
                val question2 = Question(0,question2,surveyId)
                val question3 = Question(0,question3,surveyId)
                val question4 = Question(0,question4,surveyId)
                val question5 = Question(0,question5,surveyId)
                val question6 = Question(0,question6,surveyId)
                val question7 = Question(0,question7,surveyId)
                val question8 = Question(0,question8,surveyId)
                val question9 = Question(0,question9,surveyId)
                val question10 = Question(0,question10,surveyId)

                dbHelper.addQuestion(question1)
                dbHelper.addQuestion(question2)
                dbHelper.addQuestion(question3)
                dbHelper.addQuestion(question4)
                dbHelper.addQuestion(question5)
                dbHelper.addQuestion(question6)
                dbHelper.addQuestion(question7)
                dbHelper.addQuestion(question8)
                dbHelper.addQuestion(question9)
                dbHelper.addQuestion(question10)
                val intent = Intent(this,AdminPanel::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error: The user not added", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun goBack(view: View){
        finish()
    }

    override fun onBackPressed() {
        return
    }

}