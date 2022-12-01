package com.example.surveyapp.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

private val DataBaseName = "DataBaseSurvey"
private val ver: Int = 1

class DataBaseHelper(context: Context):SQLiteOpenHelper(context, DataBaseName,null, ver) {

    /*User Table*/
    public val users = "Users"
    public val userId = "userId"
    public val userName = "userName"
    public val passWord = "passWord"
    public val isAdmin = "isAdmin"

    /*Surveys Table*/
    public val surveys = "Surveys"
    public val surveyId = "surveyId"
    public val surveyTitle = "surveyTitle"
    public val surveyStartDate = "surveyStartDate"
    public val surveyEndDate = "surveyEndDate"

    /*Questions Table*/
    public val questions = "Questions"
    public val questionId = "questionId"
    public val questionText = "questionText"
    public val questionSurveyId = "surveyId"

    /*Answers Table*/
    public val answers = "Answers"
    public val answerId = "answerId"
    public val answerQuestionId = "questionId"
    public val answerUserId = "userId"
    public val answerText = "answerText"

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String = "CREATE TABLE " + users + " ( " +
                    userId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    userName + " TEXT NOT NULL, " +
                    passWord + " TEXT NOT NULL, " +
                    isAdmin + " INTEGER DEFAULT 0 )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " +
                    surveys + " ( " +
                    surveyId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    surveyTitle + " TEXT NOT NULL, " +
                    surveyStartDate + " TEXT NOT NULL, " +
                    surveyEndDate + " TEXT, )"

            db?.execSQL(sqlCreateStatement)


            sqlCreateStatement = "CREATE TABLE " +
                    questions + " ( " +
                    questionId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    questionText + " TEXT, " +
                    questionSurveyId + " INTEGER, " + " FOREIGN KEY ($questionSurveyId) REFERENCES $surveys($surveys))"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " +
                    answers + " ( " +
                    answerId + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    answerQuestionId + " INTEGER NOT NULL, " +
                    answerUserId + " INTEGER NOT NULL, " +
                    answerText + " TEXT, " + " FOREIGN KEY ($answerUserId) REFERENCES $answers ($answerId)" + " FOREIGN KEY ($answerQuestionId ) REFERENCES $questions($questionId))"

            db?.execSQL(sqlCreateStatement)


        }catch (e: SQLException){ }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getAllUsers(): ArrayList<User>{
        val userList = ArrayList<User>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $users"

        val cursor: Cursor = db.rawQuery(sqlStatement,null)

        if ((cursor.moveToFirst()))
            do {
                val userId = cursor.getInt(0)
                val userName = cursor.getString(1)
                val passWord = cursor.getString(2)
                val x = User(userId,userName,passWord)
                userList.add(x)
            }while (cursor.moveToNext())

            cursor.close()
        db.close()

        return userList
    }

}