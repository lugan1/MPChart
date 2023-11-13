package com.softnet.mpchartstudy

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.softnet.mpchartstudy.chart.Chart

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
               viewModel.addEntry()
        }) {
            Text(text = "데이터 추가")
        }

        Chart(
            modifier = Modifier.fillMaxWidth().height(800.dp),
            initialize = viewModel::initialize
        )
    }
}
