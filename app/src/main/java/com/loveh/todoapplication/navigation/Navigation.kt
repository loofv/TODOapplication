package com.loveh.todoapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loveh.todoapplication.data.models.Constants.LIST_SCREEN
import com.loveh.todoapplication.navigation.destinations.listComposable
import com.loveh.todoapplication.navigation.destinations.taskComposable

@Composable
fun Navigation(
    navController: NavHostController
){
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToListScreen = screen.list)
    }
}
