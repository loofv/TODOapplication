package com.example.todo_application.data.models

import androidx.compose.ui.graphics.Color
import com.example.todo_application.ui.theme.HighPriorityColor
import com.example.todo_application.ui.theme.LowPriorityColor
import com.example.todo_application.ui.theme.MediumPriorityColor
import com.example.todo_application.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
   HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}