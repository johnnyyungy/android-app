package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var isHello by remember { mutableStateOf(true) }
                    var gradientColors by remember {
                        mutableStateOf(
                            listOf(
                                Color(0xFF42A5F5), // Initial Blue
                                Color(0xFF0D47A1)  // Initial Dark Blue
                            )
                        )
                    }

                    // Function to generate a random color
                    fun randomColor() = Color(
                        red = (0..255).random(),
                        green = (0..255).random(),
                        blue = (0..255).random()
                    )

                    // Function to change the gradient colors
                    fun changeGradient() {
                        gradientColors = listOf(randomColor(), randomColor())
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(colors = gradientColors)
                            )
                            .padding(innerPadding)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Greeting(message = if (isHello) "Hello" else "Goodbye", name = "Johnny")
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { isHello = !isHello }) {
                                Text(if (isHello) "Say Goodbye" else "Say Hello")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { changeGradient() }) {
                                Text("Change Background Color")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Learning is a continuous journey that expands our understanding and broadens our perspectives. It involves acquiring new knowledge and skills, often challenging our preconceived notions. Embracing the process of learning fosters personal growth and opens up opportunities for future success.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp,
                                modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            DateButton()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateButton() {
    val currentDay = remember { SimpleDateFormat("EEEE", Locale.getDefault()).format(Date()) }
    val currentDate = remember { SimpleDateFormat("dd", Locale.getDefault()).format(Date()) }
    val currentMonth = remember { SimpleDateFormat("MMMM", Locale.getDefault()).format(Date()) }

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF42A5F5)), // Background color
        shape = RoundedCornerShape(10.dp), // Corner radius
        modifier = Modifier
            .padding(10.dp)  // Padding around the button
            .width(150.dp)   // Width of the button
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = currentDay,
                fontSize = 11.sp,  // Font size for the day
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 11.sp,
            )
            Text(
                text = currentDate,
                fontSize = 25.sp,  // Larger font size for the date
                fontWeight = FontWeight.Bold,  // Bold font weight
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 5.dp, bottom = 2.dp)
            )
            Text(
                text = currentMonth,
                fontSize = 11.sp,  // Font size for the month
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 11.sp,
            )
        }
    }
}

@Composable
fun Greeting(message: String, name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$message $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF42A5F5),
                            Color(0xFF0D47A1)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Greeting(message = "Hello", name = "Android", modifier = Modifier.padding(start = 20.dp, top = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { }) {
                    Text("Change Background Color")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Learning is a continuous journey that expands our understanding and broadens our perspectives. It involves acquiring new knowledge and skills, often challenging our preconceived notions. Embracing the process of learning fosters personal growth and opens up opportunities for future success.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                DateButton()
            }
        }
    }
}
