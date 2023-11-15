package com.softnet.mpchartstudy.chart

import android.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalTime


@Composable
fun Chart(
    modifier: Modifier = Modifier,
    entries: List<Entry> = listOf(),
    visibleValue: Boolean = false
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false

                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.valueFormatter = XAxisValueFormatter()

                axisRight.isEnabled = false

                axisLeft.axisMinimum = 0f
                axisLeft.axisMaximum = 42f

                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)

                legend.isEnabled = true
                legend.form = Legend.LegendForm.LINE

                extraBottomOffset = 12f
            }
        },
        update = { lineChart ->
            val dataSet = LineDataSet(entries, "체온").apply {
                color = Color.parseColor("#6e2ef4")
                setDrawValues(visibleValue)

                valueTextSize = 10f
                circleColors = listOf(Color.parseColor("#6e2ef4"))
                circleHoleRadius = 3f
                circleRadius = 5f
                lineWidth = 2f
                mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            }

            lineChart.data = LineData(dataSet)
            lineChart.setVisibleXRangeMaximum(5f)
            lineChart.moveViewToX(dataSet.entryCount.toFloat()-1)
            //lineChart.xAxis.granularity = dataSet.entryCount.toFloat() / 5f
            lineChart.notifyDataSetChanged()
            lineChart.invalidate()
        }
    )
}

@Composable
fun DynamicChart(
    modifier: Modifier = Modifier,
    initialize: (LineChart) -> Unit = {}
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val lineChart = LineChart(context).apply {
                description.isEnabled = false

                xAxis.position = XAxis.XAxisPosition.BOTTOM
                //xAxis.setLabelCount(3, true)

                axisRight.isEnabled = false

                axisLeft.axisMinimum = 0f
                axisLeft.axisMaximum = 42f

                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)

                legend.isEnabled = true
                legend.form = Legend.LegendForm.LINE

                extraBottomOffset = 12f

                val dataSet = LineDataSet(null, "체온").apply {
                    color = Color.parseColor("#6e2ef4")
                    setDrawValues(true)

                    valueTextSize = 10f
                    circleColors = listOf(Color.parseColor("#6e2ef4"))
                    circleHoleRadius = 3f
                    circleRadius = 5f
                    lineWidth = 2f
                    mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                }

                val markerView = CustomMarkerView(context)
                markerView.chartView = this
                marker = markerView

                val lineData = LineData(dataSet)
                lineData.setValueTextColor(Color.WHITE)
                data = lineData
            }
            initialize(lineChart)
            lineChart
        },
        update = {
            it.data.getDataSetByIndex(0).setDrawValues(true)
        }
    )
}