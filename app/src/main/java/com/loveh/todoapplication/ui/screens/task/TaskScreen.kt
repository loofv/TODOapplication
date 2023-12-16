package com.loveh.todoapplication.ui.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    Scaffold(
        topBar = {
                 TaskAppBar(selectedTask = selectedTask,
                     navigateToListScreen = {action ->
                         if(action == Action.NO_ACTION) {
                             navigateToListScreen(action)
                         } else {
                             if (sharedViewModel.validateFields()) {
                                 navigateToListScreen(action)
                             } else {
                                 Toast.makeText(context, "Title and description must not be empty. ", Toast.LENGTH_LONG).show()
                             }
                         }
                     }
                     )
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