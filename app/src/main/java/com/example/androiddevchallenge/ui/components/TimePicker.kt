package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
        HourInput(isStarted)
        MinuteInput(isStarted)
        SecondInput(isStarted)
    }
}

@Composable
fun HourInput(isStarted: Boolean) {
    var hour by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = hour,
        onValueChange = { hour = it },
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
fun MinuteInput(isStarted: Boolean) {
    var minute by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = minute,
        onValueChange = { minute = it },
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
fun SecondInput(isStarted: Boolean) {
    var second by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = second,
        onValueChange = { second = it },
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