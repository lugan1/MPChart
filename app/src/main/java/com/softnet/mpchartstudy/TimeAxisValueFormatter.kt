package com.softnet.mpchartstudy

import com.github.mikephil.charting.formatter.ValueFormatter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TimeAxisValueFormatter : ValueFormatter() {
    private val formatter = DateTimeFormatter.ofPattern("HH:mm")

    override fun getFormattedValue(value: Float): String {
        val dateTime = epochToDateTime(value.toLong())
        return dateTime.format(formatter)
    }

    private fun epochToDateTime(epoch: Long): LocalDateTime {
        val instant = Instant.ofEpochMilli(epoch)
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}
