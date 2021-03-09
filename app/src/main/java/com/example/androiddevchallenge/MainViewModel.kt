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

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.models.TimerTime
import com.example.androiddevchallenge.ui.utils.convertSecondToTimer
import com.example.androiddevchallenge.ui.utils.convertToSecond
import com.example.androiddevchallenge.ui.utils.isEmpty

enum class TimerState {
    Idle,
    Start,
    Pause
}

class MainViewModel : ViewModel() {
    private val _timerState = MutableLiveData(TimerState.Idle)
    val timerState: LiveData<TimerState> = _timerState

    private lateinit var countdownTimer: CountDownTimer

    private val _time = MutableLiveData(TimerTime())
    val time: LiveData<TimerTime> = _time

    private val _timeDisplay = MutableLiveData(TimerTime())
    val timeDisplay: LiveData<TimerTime> = _timeDisplay

    fun setHour(hour: Int) {
        val timerTime = _time.value!!
        _time.value = TimerTime(hour = hour, minute = timerTime.minute, second = timerTime.second)
    }

    fun setMinute(minute: Int) {
        val timerTime = _time.value!!
        _time.value = TimerTime(hour = timerTime.hour, minute = minute, second = timerTime.second)
    }

    fun setSecond(second: Int) {
        val timerTime = _time.value!!
        _time.value = TimerTime(hour = timerTime.hour, minute = timerTime.minute, second = second)
    }

    private fun resetTimer() {
        _timeDisplay.value = TimerTime()
        _time.value = TimerTime()
        _timerState.value = TimerState.Idle
    }

    fun startTimer() {
        if (_timeDisplay.value?.isEmpty() == true) {
            _timeDisplay.value = _time.value
        }

        countdownTimer =
            object : CountDownTimer(timeDisplay.value!!.convertToSecond().toLong() * 1000, 1000) {
                override fun onTick(p0: Long) {
                    _timeDisplay.value = convertSecondToTimer((p0 / 1000).toInt())
                }

                override fun onFinish() {
                    _timerState.value = TimerState.Idle
                }
            }
        _timerState.value = TimerState.Start
        countdownTimer.start()
    }

    fun pauseTimer() {
        countdownTimer.cancel()
        _timerState.value = TimerState.Pause
    }

    fun stopTimer() {
        if (this::countdownTimer.isInitialized) {
            countdownTimer.cancel()
        }
        resetTimer()
    }

    fun getProgress(): Float {
        val timerTimeSecond = time.value!!.convertToSecond()
        val timerTimeDisplay = timeDisplay.value!!.convertToSecond()
        return if (timerTimeSecond == 0) 0f else timerTimeDisplay / timerTimeSecond.toFloat()
    }
}
