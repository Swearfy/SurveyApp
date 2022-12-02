package com.example.surveyapp.Model

import android.content.Context

class App(context: Context) {
    private val  userList: ArrayList<User>
    private val surveyList: ArrayList<Survey>
    private val questionsList: ArrayList<Question>
    private val answersList: ArrayList<Answer>
    private val dbHelper: DataBaseHelper = DataBaseHelper(context)

    init {
        userList = dbHelper.getAllUsers()
        surveyList = dbHelper.getAllSurveys()
        questionsList = dbHelper.getAllQuestions()
        answersList = dbHelper.getAllAnswers()
    }






}
