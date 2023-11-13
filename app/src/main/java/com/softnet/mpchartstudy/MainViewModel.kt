package com.softnet.mpchartstudy

import android.graphics.Color
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private var chart: LineChart? = null

    fun initialize(lineChart: LineChart) {
        chart = lineChart
    }

    fun addEntry() {
        chart?.data?.run {
            val entry = Entry(entryCount.toFloat(), Random.nextInt(35, 40).toFloat())
            addEntry(entry, 0)
            notifyDataChanged()
            chart!!.notifyDataSetChanged()
            chart!!.setVisibleXRangeMaximum(10f)
            chart!!.moveViewToX(entryCount.toFloat())
        }
    }

    fun createSet(): LineDataSet {
        return LineDataSet(null, "체온").apply {
            color = Color.parseColor("#6e2ef4")
            setDrawValues(true)

            valueTextSize = 10f
            circleColors = listOf(Color.parseColor("#6e2ef4"))
            circleHoleRadius = 3f
            circleRadius = 5f
            lineWidth = 2f
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        }
    }
}
