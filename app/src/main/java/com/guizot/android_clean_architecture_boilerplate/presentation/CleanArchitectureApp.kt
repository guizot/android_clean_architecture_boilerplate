package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guizot.android_clean_architecture_boilerplate.presentation.core.theme.AndroidCleanArchitectureBoilerplateTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.guizot.android_clean_architecture_boilerplate.presentation.core.widget.CustomAppBar
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.HomeScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.SettingScreen

@Composable
fun MyApp() {
    AndroidCleanArchitectureBoilerplateTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                CustomAppBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = "home",
                enterTransition = { EnterTransition.None},
                exitTransition = { ExitTransition.None },
                popEnterTransition = {EnterTransition.None },
                popExitTransition = { ExitTransition.None },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                composable("home") { HomeScreen() }
                composable("setting") { SettingScreen() }
            }
        }

    }
}