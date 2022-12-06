package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question

class SurveyEditPanelandDataUser : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    var surveyid = 0
    var transferUserId= 0

    var surveyQuestionList = ArrayList<Question>()
    var questionidList = ArrayList<Int>()
    var answerList = ArrayList<Answer>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_edit_paneland_data_user)

        surveyid = intent.getIntExtra("surveyid",0)
        transferUserId = intent.getIntExtra("userId",0)

        val survey = dbHelper.getSurveyById(surveyid)
        val surveyQuestions = dbHelper.getAllQuestionsBySurveyId(surveyid)

        for (question in surveyQuestions){
            surveyQuestionList.add(question)
        }

        for (question in surveyQuestionList){
            questionidList.add(question.questionId)
        }

        for (id in questionidList){
            val d = dbHelper.getAnswerbyQuestionId(id)
            answerList.add(d)
        }

        findViewById<TextView>(R.id.text_editTitle2).text = survey.surveyTitle
        findViewById<TextView>(R.id.text_editStartDate2).text = survey.surveyStartDate
        findViewById<TextView>(R.id.text_editEndDate2).text = survey.surveyEndDate

        if(transferUserId == answerList[0].userId ){
            findViewById<Button>(R.id.btn_edit2).isVisible = false
        }

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