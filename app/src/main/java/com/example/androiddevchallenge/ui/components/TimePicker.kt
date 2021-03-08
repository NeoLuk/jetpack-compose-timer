package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.MainViewModel

@Composable
fun TimePicker(isStarted: Boolean, mainViewModel: MainViewModel) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        val timerTime = mainViewModel.time.value!!
        HourInput(isStarted, timerTime.hour) { mainViewModel.setHour(hour = it) }
        MinuteInput(isStarted, timerTime.minute) { mainViewModel.setMinute(minute = it) }
        SecondInput(isStarted, timerTime.second) { mainViewModel.setSecond(second = it) }
    }
}

@Composable
fun HourInput(isStarted: Boolean, hour: Int, onChange: (Int) -> Unit) {
    var hourState: String by remember { mutableStateOf(hour.toString()) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = hourState,
        onValueChange = {
            if (it.isNotEmpty()) onChange(it.toInt()) else onChange(0)
            hourState = it
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Hour") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = !isStarted
    )
}

@Composable
fun MinuteInput(isStarted: Boolean, minute: Int, onChange: (Int) -> Unit) {
    var minuteState by rememberSaveable { mutableStateOf(minute.toString()) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = minuteState,
        onValueChange = {
            if (it.isNotEmpty()) onChange(it.toInt()) else onChange(0)
            minuteState = it
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Minute") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = !isStarted
    )
}

@Composable
fun SecondInput(isStarted: Boolean, second: Int, onChange: (Int) -> Unit) {
    var secondState by rememberSaveable { mutableStateOf(second.toString()) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = secondState,
        onValueChange = {
            if (it.isNotEmpty()) onChange(it.toInt()) else onChange(0)
            secondState = it
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        label = { Text(text = "Second") },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier.width(100.dp),
        enabled = !isStarted
    )
}