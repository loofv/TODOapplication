package com.loveh.todoapplication.ui.screens.task

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.loveh.todoapplication.components.DisplayAlertDialog
import com.loveh.todoapplication.data.models.Priority
import com.loveh.todoapplication.data.models.ToDoTask
import com.loveh.todoapplication.ui.theme.topAppBarBackgroundColor
import com.loveh.todoapplication.ui.theme.topAppBarContentColor
import com.loveh.todoapplication.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = { BackAction(onBackClicked = navigateToListScreen)},
        title = {
            Text(
                text = "Add task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}
@Composable
fun BackAction(onBackClicked : (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back arrow",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}
@Composable
fun AddAction(onAddClicked : (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Checkmark icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask
) {
    TopAppBar(
        navigationIcon = { CloseAction(onCloseClicked = navigateToListScreen) },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(onCloseClicked : (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Close icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit,
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(title = "Delete ${selectedTask.title}?",
        message = "Are you sure you want to delete ${selectedTask.title}?",
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = {
            navigateToListScreen(Action.DELETE)
        }
    )

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun DeleteAction(onDeleteClicked : () -> Unit) {
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(onUpdateClicked : (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Checkmark icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(navigateToListScreen = {}, selectedTask = ToDoTask(
        id = 0, title = "Test title", description = " Test descr", priority = Priority.NONE
    )
    )
}
