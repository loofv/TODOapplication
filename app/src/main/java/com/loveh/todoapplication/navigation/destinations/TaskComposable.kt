package com.loveh.todoapplication.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loveh.todoapplication.data.models.Constants.TASK_ARGUMENT_KEY
import com.loveh.todoapplication.data.models.Constants.TASK_SCREEN
import com.loveh.todoapplication.ui.screens.task.TaskScreen
import com.loveh.todoapplication.util.Action

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->  
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        Log.d("taskId", "taskId: $taskId")
        
        TaskScreen(navigateToListScreen = navigateToListScreen)
    }
}