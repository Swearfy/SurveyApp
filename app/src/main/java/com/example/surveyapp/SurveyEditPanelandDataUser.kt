package com.example.surveyapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SurveyEditPanelandDataUser : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var surveyid = 0
    var transferUserId = 0
    var alreadyAnswered = ArrayList<Int>()
    var questionIdList = ArrayList<Int>()
    var answerList = ArrayList<Answer>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data_user)

        surveyid = intent.getIntExtra("surveyid", 0)
        transferUserId = intent.getIntExtra("userId", 0)

        val survey = dbHelper.getSurveyById(surveyid)
        val answers = dbHelper.getAllAnswers()
        val questions = dbHelper.getAllQuestionsBySurveyId(surveyid)

        for (question in questions){
            questionIdList.add(question.questionId)
        }

        for (id in questionIdList){
            answerList = dbHelper.getAllAnswersByQuestionid(id)
        }

        for (id in 0 until answerList.size){
            alreadyAnswered.add(answerList[id].userId)
        }

        findViewById<TextView>(R.id.text_editTitle2).text = survey.surveyTitle
        var startdate = findViewById<TextView>(R.id.text_editStartDate2)
        startdate.text = survey.surveyStartDate
        var endDate = findViewById<TextView>(R.id.text_editEndDate2)
        endDate.text = survey.surveyEndDate

        val current: LocalDate = LocalDate.now()
        val localDate2: LocalDate = LocalDate.parse(endDate.text, DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        if (alreadyAnswered.contains(transferUserId) || localDate2<=current) {
            findViewById<Button>(R.id.btn_edit2).isVisible = false
        }

    }

    fun answer(view: View) {
        val intent = Intent(this, UserAnswerPanel::class.java)
        intent.putExtra("surveyid", surveyid)
        intent.putExtra("userId", transferUserId)
        startActivity(intent)
    }


    fun goBack(view: View) {
        finish()
    }

    override fun onBackPressed() {
        return
    }
}