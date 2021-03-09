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
