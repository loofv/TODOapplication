package com.loveh.todoapplication.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loveh.todoapplication.data.models.Constants.SPLASH_SCREEN
import com.loveh.todoapplication.ui.screens.splash.SplashScreen

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = SPLASH_SCREEN,
        enterTransition = {
            slideInVertically(
                animationSpec = tween(500),
            )
        },
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(1000),
                targetOffsetY = { -it/2 }
            )
        }
        ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}
