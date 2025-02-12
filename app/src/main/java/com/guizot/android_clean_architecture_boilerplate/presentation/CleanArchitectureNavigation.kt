package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubDetailScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubDetailViewModel
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubViewModel
import com.guizot.android_clean_architecture_boilerplate.presentation.home.HomeScreen
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.SettingScreen
import kotlinx.serialization.Serializable

@Composable
fun CleanArchitectureNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController,
        startDestination = Home,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        popEnterTransition = { fadeIn() },
        popExitTransition = { fadeOut() },
        modifier = modifier
    ) {

        // MAIN
        composable<Home> {
            HomeScreen(
                onClickGithub = {
                    navController.navigate(GithubList)
                }
            )
        }
        composable<Setting> { SettingScreen() }

        // GITHUB
        composable<GithubList> {
            val viewModel = hiltViewModel<GithubViewModel>()
            GithubScreen(
                viewModel = viewModel,
            ) {
                navController.navigate(GithubDetail(username = it))
            }
        }
        composable<GithubDetail> {
            val viewModel = hiltViewModel<GithubDetailViewModel>()
            val args = it.toRoute<GithubDetail>()
            GithubDetailScreen(
                viewModel = viewModel,
                username = args.username
            )
        }

    }
}

@Serializable
data object Home {
    const val ROUTE = "Home"
}

@Serializable
data object Setting {
    const val ROUTE = "Setting"
}

@Serializable
data object GithubList {
    const val ROUTE = "GithubList"
}

@Serializable
data class GithubDetail(val username: String?) {
    companion object {
        const val ROUTE = "GithubDetail"
    }
}

