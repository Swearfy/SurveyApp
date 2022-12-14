package com.example.surveyapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class BarChartSurvey : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    var arraylist = ArrayList<BarEntry>()
    val newArray = ArrayList<Int>()
    var answerList = ArrayList<Answer>()
    var chosensurveyId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart_survey)
        var getchosenid = intent.getIntExtra("surveyid", 0)
        supportActionBar?.title = ""

        chosensurveyId = getchosenid

        val questions = dbHelper.getAllQuestionsBySurveyId(chosensurveyId)

        for (question in questions){
            newArray.add(question.questionId)
        }

        for (id in newArray){
            answerList.addAll(dbHelper.getAllAnswersByQuestionid(id))
        }

        loadChartData()
    }

    fun loadChartData(){

        var entries = ArrayList<BarEntry>()
        var totalAnswers = answerList.size
        var  testarray = ArrayList<Int>()

        try {

            for (questionId in newArray){
                var strongAgree= 0
                var agree= 0
                var neither= 0
                var disagre= 0
                var strongDisagree= 0

                for (x in 0 until answerList.size){
                    if(questionId == answerList[x].questionId){
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

                var total = intArrayOf(strongAgree,agree,neither,disagre,strongDisagree)
                var total2 = (5*strongAgree + 4*agree + 3*neither + 2*disagre + 1*strongAgree)/total.sum()
                testarray.add(total2)
            }

            entries.add(BarEntry(0f,testarray[0].toFloat(),"test"))
            entries.add(BarEntry(1f,testarray[1].toFloat()))
            entries.add(BarEntry(2f,testarray[2].toFloat()))
            entries.add(BarEntry(3f,testarray[3].toFloat()))
            entries.add(BarEntry(4f,testarray[4].toFloat()))
            entries.add(BarEntry(5f,testarray[5].toFloat()))
            entries.add(BarEntry(6f,testarray[6].toFloat()))
            entries.add(BarEntry(7f,testarray[7].toFloat()))
            entries.add(BarEntry(8f,testarray[8].toFloat()))
            entries.add(BarEntry(9f,testarray[9].toFloat()))
        }catch (e: IllegalArgumentException){
            entries.add(BarEntry(0f,0f))
            entries.add(BarEntry(0f,0f))
            entries.add(BarEntry(0f,0f))
            entries.add(BarEntry(0f,0f))
            entries.add(BarEntry(0f,0f))
        }
        arraylist = entries

        var barChart = findViewById<BarChart>(R.id.bardatasurvey)
        var barDataSet = BarDataSet(arraylist,"Survey Questions")
        var barData = BarData(barDataSet)

        val colors = ArrayList<Int>()
        for(color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        for(color in ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color)
        }

        barChart.data = barData

        barDataSet.colors = colors
        barDataSet.valueTextColor = Color.BLACK
        barData.barWidth = 0.9f
        barChart.setFitBars(true)

        val xAxisLabels = listOf("Q1","Q2","Q3","Q4","Q5","Q6","Q7","Q8","Q9","Q10")
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.xAxis.labelCount = 10
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.description = null;
       var l = barChart.axisLeft
        barChart.axisRight.isEnabled = false
        l.axisMaximum = 5F
        l.labelCount = 5
        l.axisMinimum = 0f
        barChart.setPinchZoom(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.isClickable = false
        barData.isHighlightEnabled = false
        barChart.setDrawGridBackground(false);
        barChart.animateY(2000);
        barChart.legend.isEnabled = false;
        barChart.data.setValueTextSize(10f);
        barChart.invalidate()
    }


    fun exitbtn(view: View){
        finish()
    }
}