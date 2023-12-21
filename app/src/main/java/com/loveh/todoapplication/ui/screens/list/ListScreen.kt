package com.loveh.todoapplication.ui.screens.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loveh.todoapplication.ui.viewmodels.SharedViewModel
import com.loveh.todoapplication.util.Action
import com.loveh.todoapplication.util.SearchAppBarState
import kotlinx.coroutines.launch

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    // Launched Effect with key1 = true just runs once, bit of a "hack".
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortingState()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action = action) },
        taskTitle = sharedViewModel.title.value,
        onUndoClicked = {
            sharedViewModel.action.value = it
        },
        action = action
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
                 ListContent(allTasks = allTasks,
                     searchedTasks = searchedTasks,
                     searchAppBarState = searchAppBarState,
                     lowPriorityTasks = lowPriorityTasks,
                     highPriorityTasks = highPriorityTasks,
                     sortState = sortState,
                     onSwipeToDelete = { action, task ->
                         sharedViewModel.action.value = action
                         sharedViewModel.updateTaskFields(selectedTask = task)
                     },
                     navigateToTaskScreen = navigateToTaskScreen)
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
    })
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        onFabClicked(-100)
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "add icon",
            tint = Color.Black
        )
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when(action) {
        Action.DELETE_ALL -> "All tasks deleted."
        else -> "${action.name}: $taskTitle"
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,

    action: Action
) {
    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackbarResult,
                    onUndoClicked = onUndoClicked,
                )
            }
        }
    }

}
private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}