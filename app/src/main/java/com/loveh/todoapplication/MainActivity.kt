package com.loveh.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.TopAppBar
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.loveh.todoapplication.navigation.Navigation
import com.loveh.todoapplication.ui.theme.TODOapplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOapplicationTheme {
                navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}