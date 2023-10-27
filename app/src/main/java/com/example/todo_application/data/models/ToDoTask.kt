package com.example.todo_application.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class ToDoTask (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)
