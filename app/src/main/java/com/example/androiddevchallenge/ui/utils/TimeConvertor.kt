package com.example.androiddevchallenge.ui.utils

import android.util.Log
import com.example.androiddevchallenge.models.TimerTime
import com.example.androiddevchallenge.ui.components.TimePicker

fun convertTimerToSecond(timerTime: TimerTime): Int {
    return timerTime.hour * 60 * 60 + timerTime.minute * 60 + timerTime.second
}

fun convertSecondToTimer(seconds: Int): TimerTime {
    if (seconds <= 0) return TimerTime()

    val hour = seconds / 3600
    val minute = (seconds - 3600 * hour) / 60
    val second = seconds - hour * 3600 - minute * 60

    return TimerTime(
        hour = hour,
        minute = minute,
        second = second
    )
}