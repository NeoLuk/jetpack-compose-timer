package com.example.androiddevchallenge.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.TimerState
import com.example.androiddevchallenge.models.TimerTime

@Composable
fun TimePicker(timerState: TimerState, mainViewModel: MainViewModel) {
    val timerTime = mainViewModel.time.observeAsState()

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HourInput(timerState, timerTime) { mainViewModel.setHour(hour = it) }
        MinuteInput(timerState, timerTime) { mainViewModel.setMinute(minute = it) }
        SecondInput(timerState, timerTime) { mainViewModel.setSecond(second = it) }
    }
}

@Composable
fun HourInput(timerState: TimerState, timerTime: State<TimerTime?>, onChange: (Int) -> Unit) {
    val focusManager = LocalFocusManager.current
    Log.d("?????", "HourInput: build${timerTime.value}")
    val text = if (timerTime.value?.hour == 0) "" else timerTime.value?.hour.toString()


    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.isDigitsOnly()) {
                    onChange(it.toInt())
                }
            } else {
                onChange(0)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Hour") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = timerState == TimerState.Idle
    )
}

@Composable
fun MinuteInput(timerState: TimerState, timerTime: State<TimerTime?>, onChange: (Int) -> Unit) {
    val focusManager = LocalFocusManager.current
    val text = if (timerTime.value?.minute == 0) "" else timerTime.value?.minute.toString()

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.isDigitsOnly()) {
                    onChange(it.toInt())
                }
            } else {
                onChange(0)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Minute") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = timerState == TimerState.Idle
    )
}

@Composable
fun SecondInput(timerState: TimerState, timerTime: State<TimerTime?>, onChange: (Int) -> Unit) {
    val focusManager = LocalFocusManager.current
    val text = if (timerTime.value?.second == 0) "" else timerTime.value?.second.toString()

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.isDigitsOnly()) {
                    onChange(it.toInt())
                }
            } else {
                onChange(0)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Second") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = timerState == TimerState.Idle
    )
}