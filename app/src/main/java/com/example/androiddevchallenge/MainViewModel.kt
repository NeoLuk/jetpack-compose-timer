package com.example.androiddevchallenge

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.models.TimerTime
import com.example.androiddevchallenge.ui.utils.convertSecondToTimer
import com.example.androiddevchallenge.ui.utils.convertToSecond


class MainViewModel : ViewModel() {
    private val _isStarted = MutableLiveData(false)
    val isStarted: LiveData<Boolean> = _isStarted
    lateinit var countdownTimer: CountDownTimer


    private val _time = MutableLiveData(TimerTime())
    val time: LiveData<TimerTime> = _time

    fun setHour(hour: Int) {
        Log.d("fcuk", "setHour: $hour")
        _time.value = TimerTime(hour = hour)
    }

    fun setMinute(minute: Int) {
        _time.value?.minute = minute
    }

    fun setSecond(second: Int) {
        _time.value?.second = second
    }

    fun startTimer() {
        countdownTimer =
            object : CountDownTimer(time.value!!.convertToSecond().toLong() * 1000, 1000) {
                override fun onTick(p0: Long) {
                    Log.d("fcuk", "onTick: $p0")
                    _time.value = convertSecondToTimer((p0 / 1000).toInt())
                }

                override fun onFinish() {
                    Log.d("fcuk", "onFinish: ")
                    _isStarted.value = false
                }

            }
        countdownTimer.start()
        _isStarted.value = true

    }

    fun stopTimer() {
        countdownTimer.cancel()
        _isStarted.value = false
    }
}