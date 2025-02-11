package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.github.GithubScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.github.GithubViewModel
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.home.HomeScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.setting.SettingScreen

@Composable
fun CleanArchitectureNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = CleanArchitectureNavigation.HOME,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        modifier = modifier
    ) {
        composable(CleanArchitectureNavigation.HOME) {
            HomeScreen(
                onClickGithub = {
                    navController.navigate(CleanArchitectureNavigation.GITHUB)
                }
            )
        }
        composable(CleanArchitectureNavigation.SETTING) { SettingScreen() }
        composable(CleanArchitectureNavigation.GITHUB) {
            val viewModel = hiltViewModel<GithubViewModel>()
            GithubScreen(
                viewModel = viewModel
            )
        }
    }
}

object CleanArchitectureNavigation {
    const val HOME = "Home"
    const val SETTING = "Setting"
    const val GITHUB = "Github"
}

//@Serializable
//data object Home {
//    const val NAME = "Home"
//}

//@Serializable
//data object Setting {
//    const val NAME = "Setting"
//}