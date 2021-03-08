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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.models.TimerTime
import com.example.androiddevchallenge.ui.components.TimePicker
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.utils.isEmpty

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(darkTheme = isSystemInDarkTheme()) {
                MyApp(mainViewModel)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(mainViewModel: MainViewModel) {
    val focusManager = LocalFocusManager.current
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val timerTime: TimerTime by mainViewModel.time.observeAsState(TimerTime())
            val isStarted: Boolean by mainViewModel.isStarted.observeAsState(false)


            if (isStarted) {
                Box(modifier = Modifier.size(250.dp)) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize(),
                        strokeWidth = 12.dp, progress = 0.0f,
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "${timerTime.hour}:${timerTime.minute}:${timerTime.second}",
                        fontSize = 35.sp
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }



            TimePicker(isStarted, mainViewModel)

            Spacer(modifier = Modifier.height(60.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (isStarted) {
                        mainViewModel.stopTimer()
                    } else {
                        mainViewModel.startTimer()
                    }
                },
                shape = CircleShape,
                modifier = Modifier.size(100.dp),
                enabled = !timerTime.isEmpty()
            ) {
                Text(text = if (isStarted) "Stop" else "Start")
            }
        }

    }
}
