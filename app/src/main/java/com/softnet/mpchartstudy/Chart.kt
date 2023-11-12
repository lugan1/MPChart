package com.softnet.mpchartstudy

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun Chart(
    modifier: Modifier = Modifier,
    lineData: LineData? = null
) {


    if(lineData != null) {
        // AndroidView to integrate MPAndroidChart
        AndroidView(
            modifier = modifier,
            factory = { context ->
                LineChart(context).apply {
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.valueFormatter = MyXAxisFormatter()

                    axisRight.isEnabled = false
                    legend.isEnabled = false

                    axisLeft.axisMinimum = 20f
                    axisLeft.axisMaximum = 42f
                }
            },
            update = { view ->
                view.data = lineData
                view.setVisibleXRangeMaximum(7f)


                //view.xAxis.valueFormatter = MyXAxisFormatter()

                view.moveViewToX(lineData.entryCount.toFloat())
            }
        )
    }
}


@Preview
@Composable
fun ChartPreview() {
    val zoneId = ZoneId.systemDefault()

    val dateTime1 = LocalDateTime.of(2023, 11, 11, 0, 0)
        .atZone(zoneId).toInstant().toEpochMilli()
    val dateTime2 = LocalDateTime.of(2023, 11, 11, 0, 1)
        .atZone(zoneId).toInstant().toEpochMilli()
    val dateTime3 = LocalDateTime.of(2023, 11, 11, 0, 2)
        .atZone(zoneId).toInstant().toEpochMilli()

    val dataSet = LineDataSet(listOf(
        Entry(10f, 10f),
        Entry(20f, 20f),
        Entry(30f, 30f),
    ), "Line Chart").apply {
        color = Color.BLUE
        setDrawCircles(true)
        setDrawValues(true)
    }

    Chart(
        modifier = Modifier.fillMaxWidth().height(800.dp),
        lineData = LineData(dataSet)
    )
}

