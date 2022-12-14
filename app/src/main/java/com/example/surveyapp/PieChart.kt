package com.example.surveyapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class PieChart : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    var chosensurveyId = 0
    lateinit var pieChart: PieChart

    val newArray = ArrayList<Int>()
    var answerList = ArrayList<Answer>()
    var entries2 = ArrayList<PieEntry>()

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        supportActionBar?.title = ""

        pieChart = findViewById(R.id.piechart)

        findViewById<Button>(R.id.button4).isVisible = false
        var getchosenid = intent.getIntExtra("surveyid", 0)
        chosensurveyId = getchosenid

        val questions = dbHelper.getAllQuestionsBySurveyId(chosensurveyId)

        for (question in questions) {
            newArray.add(question.questionId)
        }

        for (id in newArray) {
            answerList.addAll(dbHelper.getAllAnswersByQuestionid(id))
        }
        pieChart.centerText = "Question 1"
        loadChartData()
    }


    fun nextQuestion(view: View) {
        if (index + 1 != newArray.size) {
            index++
            pieChart.centerText = "Question ${index + 1}"
            loadChartData()
            findViewById<Button>(R.id.button4).isVisible = true

            if (index + 1 == newArray.size) {
                findViewById<Button>(R.id.button2).isVisible = false
            }
        }
    }


    fun previous(view: View) {
        if (index + 1 != newArray.size || index + 1 == newArray.size) {
            index--
            pieChart.centerText = "Question ${index + 1}"
            loadChartData()
            findViewById<Button>(R.id.button2).isVisible = true
        }
        if (index + 1 == 1) {
            findViewById<Button>(R.id.button4).isVisible = false
        }
    }

    fun exit(view: View) {
        finish()
    }


    fun loadChartData() {

        var entries = ArrayList<PieEntry>()

        var totalAnswers = answerList.size

        try {
            var strongAgree = 0
            var agree = 0
            var neither = 0
            var disagre = 0
            var strongDisagree = 0
            for (x in 0 until answerList.size) {
                if (newArray[index] == answerList[x].questionId) {
                    when (answerList[x].answerText) {
                        "1.Strongly Agree" -> {
                            strongAgree++
                        }
                        "2.Agree" -> {
                            agree++
                        }
                        "3.Neither Agree nor Disagree" -> {
                            neither++
                        }
                        "4.Disagree" -> {
                            disagre++
                        }
                        "5.Strongly Disagree" -> {
                            strongDisagree++
                        }
                    }
                }
            }
            var a = strongAgree.toDouble() / (totalAnswers / 10) * 100
            var b = agree.toDouble() / (totalAnswers / 10) * 100
            var c = neither.toDouble() / (totalAnswers / 10) * 100
            var d = disagre.toDouble() / (totalAnswers / 10) * 100
            var x = strongDisagree.toDouble() / (totalAnswers / 10) * 100

            entries.add(PieEntry(a.toFloat(), "Strongly Agree"))
            entries.add(PieEntry(b.toFloat(), "Agree"))
            entries.add(PieEntry(c.toFloat(), "Nighter  "))
            entries.add(PieEntry(d.toFloat(), "Disagree"))
            entries.add(PieEntry(x.toFloat(), "Strongly Disagree"))
        } catch (e: IllegalArgumentException) {
            entries.add(PieEntry(index.toFloat(), "Strongly Agree"))
            entries.add(PieEntry(index.toFloat(), "Agree"))
            entries.add(PieEntry(index.toFloat(), "Nighter  "))
            entries.add(PieEntry(index.toFloat(), "Disagree"))
            entries.add(PieEntry(index.toFloat(), "Strongly Disagree"))
        }
        entries2 = entries

        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        var dataSet = PieDataSet(entries2, "Question Answers")
        dataSet.colors = colors

        val data = PieData(dataSet)

        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12F)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setCenterTextSize(24f)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.animateX(1000)
        pieChart.data = data
        pieChart.invalidate()
    }
}