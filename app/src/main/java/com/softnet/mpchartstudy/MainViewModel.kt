package com.softnet.mpchartstudy

import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.random.Random

class MainViewModel: ViewModel() {
    private val dataSet = LineDataSet(mutableListOf(), "Line Chart").apply {
        color = Color.BLUE
        setDrawCircles(true)
        setDrawValues(true)
        mode = LineDataSet.Mode.HORIZONTAL_BEZIER
    }

    private val now = LocalDateTime.now()
    private var count: Long = 0

    var lineData by mutableStateOf<LineData?>(null)

    fun addEntry() {
        val size = dataSet.entryCount.toFloat()
        val yValue = 32.2f + Random.nextFloat() * (40.2f - 32.2f)

        val zoneId = ZoneId.systemDefault()
        val time = now.plusMinutes(count).atZone(zoneId).toInstant().toEpochMilli()
        count += 1

        val entry = Entry(size, yValue)

        dataSet.addEntry(entry)
        lineData = LineData(dataSet)
    }
}
