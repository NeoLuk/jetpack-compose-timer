/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import com.example.androiddevchallenge.models.TimerTime
import com.example.androiddevchallenge.ui.utils.convertSecondToTimer
import com.example.androiddevchallenge.ui.utils.convertToSecond
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TimerTimeTest {
    @Test
    fun convert_empty_time() {
        val emptyTime = TimerTime()
        assertEquals(0, emptyTime.convertToSecond())
    }

    @Test
    fun convert_hour_to_second() {
        val oneHour = TimerTime(hour = 1)
        assertEquals(3600, oneHour.convertToSecond())
    }

    @Test
    fun convert_minute_to_second() {
        val oneMinute = TimerTime(minute = 1)
        assertEquals(60, oneMinute.convertToSecond())
    }

    @Test
    fun convert_second_to_second() {
        val oneSecond = TimerTime(second = 1)
        assertEquals(1, oneSecond.convertToSecond())
    }

    @Test
    fun convert_complex_time_to_second() {
        val oneAndHalfHour = TimerTime(hour = 1, minute = 30)
        val twoMinuteAndTwentySecond = TimerTime(minute = 2, second = 20)
        val oneHourAndOneSecond = TimerTime(hour = 1, second = 1)
        assertEquals(5400, oneAndHalfHour.convertToSecond())
        assertEquals(140, twoMinuteAndTwentySecond.convertToSecond())
        assertEquals(3601, oneHourAndOneSecond.convertToSecond())
    }

    @Test
    fun convert_second_to_timer() {
        assertEquals(TimerTime(), convertSecondToTimer(-4))
        assertEquals(TimerTime(), convertSecondToTimer(0))
        assertEquals(TimerTime(minute = 1), convertSecondToTimer(60))
        assertEquals(TimerTime(hour = 1), convertSecondToTimer(3600))
        assertEquals(TimerTime(hour = 1, minute = 30), convertSecondToTimer(5400))
        assertEquals(TimerTime(minute = 2, second = 20), convertSecondToTimer(140))
        assertEquals(TimerTime(hour = 1, second = 1), convertSecondToTimer(3601))
    }
}
