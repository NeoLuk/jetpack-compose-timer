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

    lateinit var countdownTimer: CountDownTimer


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
        countdownTimer.start()
        _timerState.value = TimerState.Start

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
        _timerState.value = TimerState.Idle
    }

    fun getProgress(): Float {
        return timeDisplay.value!!.convertToSecond() / time.value!!.convertToSecond().toFloat()
    }
}