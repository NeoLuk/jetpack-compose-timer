package com.example.androiddevchallenge

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    private val _isStarted = MutableLiveData(false)
    val isStarted: LiveData<Boolean> = _isStarted
    lateinit var countdownTimer: CountDownTimer


    private val _time = MutableLiveData(0)
    val time: LiveData<Int> = _time

    fun startTimer(time: Int) {
        _time.value = time
        countdownTimer = object : CountDownTimer(time.toLong() * 1000, 1000) {
            override fun onTick(p0: Long) {
                Log.d("fcuk", "onTick: $p0")
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