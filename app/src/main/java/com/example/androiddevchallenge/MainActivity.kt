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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.components.TimePicker
import com.example.androiddevchallenge.ui.theme.MyTheme

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
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val isStarted: Boolean by mainViewModel.isStarted.observeAsState(false)

            TimePicker(isStarted, mainViewModel)
            Button(
                onClick = {
                    if (isStarted) {
                        mainViewModel.stopTimer()
                    } else {
                        mainViewModel.startTimer(10)
                    }
                },
                shape = CircleShape,
                modifier = Modifier.size(100.dp),
            ) {
                Text(text = if (isStarted) "Stop" else "Start")
            }
        }

    }
}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun LightPreview() {
//    MyTheme {
//        MyApp()
//    }
//}
//
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
//}
