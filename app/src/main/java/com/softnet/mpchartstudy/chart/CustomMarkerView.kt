package com.softnet.mpchartstudy.chart

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.softnet.mpchartstudy.R

class CustomMarkerView: MarkerView {

    private val tvContent: TextView = findViewById(R.id.tvContent)
    private lateinit var xAxisValueFormatter: XAxisValueFormatter

    constructor(context: Context?, xAxisValueFormatter: XAxisValueFormatter) : super(context, R.layout.custom_marker_view) {
        this.xAxisValueFormatter = xAxisValueFormatter

    }

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        super.refreshContent(entry, highlight)
        val tvContent = findViewById<TextView>(R.id.tvContent)
        tvContent.text = entry?.y?.toString()
    }
}