package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.HomeScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.SettingScreen

@Composable
fun CleanArchitectureNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = CleanArchitectureNavigation.HOME,
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None },
        popEnterTransition = {EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        modifier = modifier
    ) {
        composable(CleanArchitectureNavigation.HOME) { HomeScreen() }
        composable(CleanArchitectureNavigation.SETTING) { SettingScreen() }
    }
}

object CleanArchitectureNavigation {
    const val HOME = "Home"
    const val SETTING = "Setting"
}

//@Serializable
//data object Home {
//    const val NAME = "Home"
//}
//
//@Serializable
//data object Setting {
//    const val NAME = "Setting"
//}