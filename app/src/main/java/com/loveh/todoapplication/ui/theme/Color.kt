package com.loveh.todoapplication.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color.LightGray
val MediumGray = Color.Gray
val DarkGray = Color.DarkGray

val LowPriorityColor = Color.Green
val MediumPriorityColor = Color.Yellow
val HighPriorityColor = Color.Red
val NonePriorityColor = Color.Gray

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) Purple80 else Color.Black
