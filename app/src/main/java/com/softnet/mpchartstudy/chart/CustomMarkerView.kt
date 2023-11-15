package com.softnet.mpchartstudy.chart

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.softnet.mpchartstudy.R

class CustomMarkerView(
    context: Context?
) : MarkerView(context, R.layout.custom_marker_view) {

    private val xValue: TextView = findViewById(R.id.xValue)
    private val yValue: TextView = findViewById(R.id.yValue)

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        super.refreshContent(entry, highlight)
        xValue.text = entry?.x.toString()
        yValue.text = entry?.y.toString()
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
    }
}