package com.loveh.todoapplication.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.loveh.todoapplication.data.models.Priority
import com.loveh.todoapplication.data.models.ToDoTask
import com.loveh.todoapplication.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
                 TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        },
        content = {
            TaskContent(
                title = "title",
                onTitleChange = {} ,
                description = "description text",
                onDescriptionChange = {},
                priority = Priority.HIGH,
                onPrioritySelected = {}
            )
        }
    )
}