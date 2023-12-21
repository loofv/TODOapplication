package com.loveh.todoapplication.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loveh.todoapplication.data.models.Constants.LIST_ARGUMENT_KEY
import com.loveh.todoapplication.data.models.Constants.LIST_SCREEN
import com.loveh.todoapplication.data.models.Constants.SPLASH_SCREEN
import com.loveh.todoapplication.ui.screens.list.ListScreen
import com.loveh.todoapplication.ui.screens.splash.SplashScreen
import com.loveh.todoapplication.ui.viewmodels.SharedViewModel
import com.loveh.todoapplication.util.toAction

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(route = SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}
