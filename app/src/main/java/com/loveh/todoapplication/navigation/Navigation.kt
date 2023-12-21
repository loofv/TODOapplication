package com.loveh.todoapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loveh.todoapplication.data.models.Constants.LIST_SCREEN
import com.loveh.todoapplication.data.models.Constants.SPLASH_SCREEN
import com.loveh.todoapplication.navigation.destinations.listComposable
import com.loveh.todoapplication.navigation.destinations.splashComposable
import com.loveh.todoapplication.navigation.destinations.taskComposable
import com.loveh.todoapplication.ui.viewmodels.SharedViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
){
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {
        splashComposable(navigateToListScreen = screen.splash)
        listComposable(navigateToTaskScreen = screen.list, sharedViewModel = sharedViewModel)
        taskComposable(navigateToListScreen = screen.task, sharedViewModel = sharedViewModel)
    }
}
