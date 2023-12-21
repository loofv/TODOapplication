package com.loveh.todoapplication.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loveh.todoapplication.data.models.Constants.TASK_ARGUMENT_KEY
import com.loveh.todoapplication.data.models.Constants.TASK_SCREEN
import com.loveh.todoapplication.ui.screens.task.TaskScreen
import com.loveh.todoapplication.ui.viewmodels.SharedViewModel
import com.loveh.todoapplication.util.Action

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInVertically(
                animationSpec = tween(durationMillis = 300),
                initialOffsetY = { it }
            )
        }
    ) { navBackStackEntry ->  
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
        if (selectedTask != null || taskId == -100) {
            sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(navigateToListScreen = navigateToListScreen, sharedViewModel = sharedViewModel, selectedTask = selectedTask)
    }
}