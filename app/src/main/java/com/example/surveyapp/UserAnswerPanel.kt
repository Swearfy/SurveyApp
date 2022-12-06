package com.example.surveyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.example.surveyapp.Model.Question

class UserAnswerPanel : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    var surveyId = 0
    var userId = 0
    var index = 0
    val newArray = ArrayList<Question>()
    val answerArray = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_answer_panel)

        userId = intent.getIntExtra("surveyid",0)
        surveyId = intent.getIntExtra("userId",0)

        val questions = dbHelper.getAllQuestionsBySurveyId(surveyId)

        for (question in questions){
            newArray.add(question)
        }

        findViewById<TextView>(R.id.text_Question).text = newArray[0].questionText

        findViewById<Button>(R.id.btn_completeAnswer).isVisible = false
        findViewById<Button>(R.id.btn_Previous).isVisible = false

        checkSelected()
    }


    fun nextQuestion(view: View){
        checkSelected()
        if (index+1 != newArray.size){
            index++
            findViewById<TextView>(R.id.text_Question).text = newArray[index].questionText
            findViewById<Button>(R.id.btn_Previous).isVisible = true
        }

        if (index+1 == newArray.size)
        {
            findViewById<Button>(R.id.btn_completeAnswer).isVisible = true
            findViewById<Button>(R.id.btn_NextQuestion).isVisible = false
        }
    }

    fun previousQuestion(view: View){
        if (index+1 <= newArray.size){
            findViewById<Button>(R.id.btn_completeAnswer).isVisible = false
            findViewById<Button>(R.id.btn_NextQuestion).isVisible = true
        }

        if (index+1 != newArray.size || index+1 == newArray.size){
            index--
            answerArray.removeAt(index)
            findViewById<TextView>(R.id.text_Question).text = newArray[index].questionText
        }

        if (index+1 == 1)
        {
            findViewById<Button>(R.id.btn_Previous).isVisible = false

        }
    }

    fun complete(view: View){

        checkSelected()
        val answer1 = Answer(0,newArray[0].questionId,userId,answerArray[0])
        dbHelper.addAnswer(answer1)

        val answer2 = Answer(0,newArray[1].questionId,userId,answerArray[1])
        dbHelper.addAnswer(answer2)

        val answer3 = Answer(0,newArray[2].questionId,userId,answerArray[2])
        dbHelper.addAnswer(answer3)

        val answer4 = Answer(0,newArray[3].questionId,userId,answerArray[3])
        dbHelper.addAnswer(answer4)

        val answer5 = Answer(0,newArray[4].questionId,userId,answerArray[4])
        dbHelper.addAnswer(answer5)

        val answer6 = Answer(0,newArray[5].questionId,userId,answerArray[5])
        dbHelper.addAnswer(answer6)

        val answer7 = Answer(0,newArray[6].questionId,userId,answerArray[6])
        dbHelper.addAnswer(answer7)

        val answer8 = Answer(0,newArray[7].questionId,userId,answerArray[7])
        dbHelper.addAnswer(answer8)

        val answer9 = Answer(0,newArray[8].questionId,userId,answerArray[8])
        dbHelper.addAnswer(answer9)

        val answer10 = Answer(0,newArray[9].questionId,userId,answerArray[9])
        dbHelper.addAnswer(answer10)

        val intent = Intent(this,UserPanel::class.java)
        intent.putExtra("userId",userId)
        startActivity(intent)
    }

    fun checkSelected(){
        val button1 = findViewById<RadioButton>(R.id.radioButton)
        val button2 = findViewById<RadioButton>(R.id.radioButton2)
        val button3 = findViewById<RadioButton>(R.id.radioButton3)
        val button4 = findViewById<RadioButton>(R.id.radioButton4)
        val button5 = findViewById<RadioButton>(R.id.radioButton5)

        if (button1.isChecked){
            answerArray.add(button1.text.toString())
        }
        if (button2.isChecked){
            answerArray.add(button2.text.toString())
        }
        if (button3.isChecked){
            answerArray.add(button3.text.toString())
        }
        if (button4.isChecked){
            answerArray.add(button4.text.toString())
        }
        if (button5.isChecked){
            answerArray.add(button5.text.toString())
        }
    }



    fun cancelAnswers(view: View){
        val intent = Intent(this,UserPanel::class.java)
        intent.putExtra("userId",userId)
        startActivity(intent)
    }

    override fun onBackPressed() {
        return
    }
}