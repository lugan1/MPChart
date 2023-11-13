package com.softnet.mpchartstudy.chart

import com.github.mikephil.charting.formatter.ValueFormatter

class XAxisValueFormatter: ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toLocalTime().toString()
    }
}