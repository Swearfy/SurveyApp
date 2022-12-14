package com.example.surveyapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.surveyapp.Model.Answer
import com.example.surveyapp.Model.DataBaseHelper
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class BarchartAllSurveys : AppCompatActivity() {
    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    var arraylist = ArrayList<BarEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barchart_all_surveys)
        supportActionBar?.title = ""
        loadChartData()
    }


    fun loadChartData() {

        var entries = ArrayList<BarEntry>()
        var testarray = ArrayList<Int>()

        var surveys = dbHelper.getAllSurveys()
        val xAxisLabels = ArrayList<String>()

        try {
            for (survey in surveys) {
                var strongAgree = 0
                var agree = 0
                var neither = 0
                var disagre = 0
                var strongDisagree = 0

                var arrayTest = ArrayList<Answer>()
                xAxisLabels.add(survey.surveyTitle)

                var questions = dbHelper.getAllQuestionsBySurveyId(survey.surveyId)
                for (question in questions) {
                    var answers = dbHelper.getAllAnswersByQuestionid(question.questionId)

                    for (answer in answers) {
                        arrayTest.add(answer)
                    }
                }

                for (answer in arrayTest) {
                    when (answer.answerText) {
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

                var total = intArrayOf(strongAgree, agree, neither, disagre, strongDisagree)
                if (total.sum() > 0) {
                    var total2 =
                        (5 * strongAgree + 4 * agree + 3 * neither + 2 * disagre + 1 * strongAgree) / total.sum()
                    testarray.add(total2)
                }
                if (total.sum() == 0) {
                    var total2 =
                        (5 * strongAgree + 4 * agree + 3 * neither + 2 * disagre + 1 * strongAgree) / 1
                    testarray.add(total2)
                }
            }
            for (index in 0 until surveys.size) {
                entries.add(BarEntry(index.toFloat(), testarray[index].toFloat(), "test"))
            }
        } catch (e: IllegalArgumentException) {
            entries.add(BarEntry(0f, 0f))
            entries.add(BarEntry(0f, 0f))
        } catch (e: IndexOutOfBoundsException) {
        }

        arraylist = entries

        var barChart = findViewById<BarChart>(R.id.barDataAllSurvey)
        var barDataSet = BarDataSet(arraylist, "Survey Questions")
        var barData = BarData(barDataSet)

        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        barChart.data = barData

        barDataSet.colors = colors
        barDataSet.valueTextColor = Color.BLACK
        barData.barWidth = 1f
        barChart.setFitBars(false)

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        barChart.xAxis.labelCount = xAxisLabels.size
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.description = null;
        var l = barChart.axisLeft
        barChart.axisRight.isEnabled = false
        l.axisMaximum = 5F
        l.labelCount = 5
        l.axisMinimum = 0f
        barChart.setPinchZoom(true);
        barChart.setScaleEnabled(true);
        barChart.setDrawBarShadow(false);
        barChart.isClickable = true
        barData.isHighlightEnabled = false
        barChart.setDrawGridBackground(false);
        barChart.animateY(2000);
        barChart.legend.isEnabled = false;
        barChart.data.setValueTextSize(1f);
        barChart.invalidate()
    }
}