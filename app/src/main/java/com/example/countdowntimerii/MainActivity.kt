package com.example.countdowntimerii

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.countdowntimerii.ui.theme.CountdownTimerIITheme
import java.util.Timer
const val TIMER_KEY = "KEYA"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountdownTimerIITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Timer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Timer( modifier: Modifier = Modifier) {

    Column() {


        Text(
            text = "Enter Timer Starting Value!",
            modifier = modifier
        )

        var timerValue by rememberSaveable {mutableStateOf("") }
        val context = LocalContext.current

        TextField(
            value = timerValue,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {timerValue = it}

        )
        Button(onClick = {
            context.startService(
                Intent(context, MyTimerService::class.java).apply {
                    putExtra(TIMER_KEY,timerValue.toInt())
                }
            )

        }) { Text("Start") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CountdownTimerIITheme {
        Timer()
    }
}