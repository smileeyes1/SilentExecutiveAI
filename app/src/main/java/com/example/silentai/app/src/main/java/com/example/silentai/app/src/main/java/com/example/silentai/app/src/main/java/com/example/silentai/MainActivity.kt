package com.example.silentai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel = AiViewModel()
            var input by remember { mutableStateOf("") }
            var output by remember { mutableStateOf("") }

            MaterialTheme {

                Scaffold { padding ->

                    Column(
                        modifier = Modifier
                            .padding(padding)
                            .padding(16.dp)
                    ) {

                        TextField(
                            value = input,
                            onValueChange = { input = it },
                            label = { Text("اكتب الاستدعاء") },
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Button(
                            onClick = {
                                viewModel.invoke(input) {
                                    output = it
                                }
                            },
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Text("Invoke")
                        }

                        Text(text = output)
                    }
                }
            }
        }
    }
}
