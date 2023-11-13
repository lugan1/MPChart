package com.softnet.mpchartstudy.chart

import java.time.LocalTime

fun LocalTime.toFloat(): Float {
    val minutes = hour * 60.0f + minute + second / 60.0f
    return minutes / 1440.0f
}

fun Float.toLocalTime(): LocalTime {
    val totalMinutes = (this * 1440.0f).toInt()
    val hour = totalMinutes / 60
    val minute = totalMinutes % 60
    return LocalTime.of(hour, minute)
}