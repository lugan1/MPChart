package com.softnet.mpchartstudy

import android.util.Log
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DateFormat
import java.util.*
import kotlin.math.round

class MyXAxisFormatter: ValueFormatter() {
    private val xValues = listOf("월", "화", "수", "목", "금", "토", "일")

    override fun getFormattedValue(value: Float): String {
        val emissionsMilliSince1970Time = value.toLong() * 1000L
        val timeMilliseconds = Date(emissionsMilliSince1970Time)
        val dateTimeFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

        return dateTimeFormat.format(timeMilliseconds)
    }
}
