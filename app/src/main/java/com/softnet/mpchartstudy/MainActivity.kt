package com.softnet.mpchartstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}


@Composable
fun MainScreen() {
    var lineData by remember { mutableStateOf(listOf<Entry>()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Button(onClick = {
            val newData = listOf(
                Entry(lineData.size.toFloat(), (0..100).random().toFloat())
            )

            lineData = lineData + newData
        }) {
            Text(text = "데이터 추가")
        }
        
        Chart(
            modifier = Modifier.fillMaxWidth().height(800.dp),
            dataSet = lineData
        )
    }
}

@Composable
fun Chart(
    modifier: Modifier = Modifier,
    dataSet: List<Entry>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = true
                description.text = "설명 텍스트"


                //xAxis.enableGridDashedLine(10f, 10f, 0f)

                //axisLeft.enableGridDashedLine(10f, 10f, 0f)
                axisLeft.axisMaximum = 100f
                axisLeft.axisMinimum = 0f

                // draw points over time
                animateX(1500)

                legend.form = Legend.LegendForm.LINE

                val entries = listOf(
                    Entry(1f, 10f),
                    Entry(2f, 20f),
                    Entry(3f, 60f),
                )

                // dataSet
                val initialDataSet = LineDataSet(entries, "DataSet").apply {
                    setDrawCircles(true)
                    setDrawValues(true)
                    setDrawFilled(true)
                    lineWidth = 1f
                    color = Color(0xFF6200EE).toArgb()
                    fillColor = Color(0xFF6200EE).toArgb()
                }

                data = LineData(initialDataSet)
            }
        },
        update = { lineChart ->
            lineChart.data = LineData(LineDataSet(dataSet, "다이나믹 데이터"))
            lineChart.data.notifyDataChanged()
            lineChart.notifyDataSetChanged()
            lineChart.invalidate()
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

