package com.example.androiddevchallenge.ui.utils

import com.example.androiddevchallenge.models.TimerTime

fun TimerTime.convertToSecond(): Int {
    return this.hour * 60 * 60 + this.minute * 60 + this.second
}

fun TimerTime.isEmpty(): Boolean {
    return this.hour == 0 && this.minute == 0 && this.second == 0
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