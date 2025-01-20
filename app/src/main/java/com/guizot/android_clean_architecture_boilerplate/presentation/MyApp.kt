package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import com.guizot.android_clean_architecture_boilerplate.presentation.core.theme.AndroidCleanArchitectureBoilerplateTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.HomeScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.SettingScreen

@Composable
fun MyApp() {
    AndroidCleanArchitectureBoilerplateTheme {
        val navController = rememberNavController()
        NavHost(
            navController,
            startDestination = "home",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable("home") { HomeScreen(navController) }
            composable("setting") { SettingScreen(navController) }
        }
    }
}