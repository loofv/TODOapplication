package com.loveh.todoapplication.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.loveh.todoapplication.data.models.Priority
import com.loveh.todoapplication.data.models.ToDoTask
import com.loveh.todoapplication.ui.viewmodels.SharedViewModel
import com.loveh.todoapplication.util.Action

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
                 TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                                sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                                      sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}