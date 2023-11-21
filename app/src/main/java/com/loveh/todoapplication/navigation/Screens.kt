package com.loveh.todoapplication.navigation

import androidx.navigation.NavHostController
import com.loveh.todoapplication.data.models.Constants.LIST_SCREEN
import com.loveh.todoapplication.util.Action

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true}
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}