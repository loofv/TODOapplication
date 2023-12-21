package com.loveh.todoapplication.navigation

import androidx.navigation.NavHostController
import com.loveh.todoapplication.data.models.Constants.LIST_SCREEN
import com.loveh.todoapplication.data.models.Constants.SPLASH_SCREEN
import com.loveh.todoapplication.util.Action

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }
    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }
    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}