package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CleanArchitectureAppBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val previousBackStackEntry = navController.previousBackStackEntry
    val currentDestination = currentBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(
                getTitle(currentDestination),
            )
        },
        navigationIcon = {
            if (previousBackStackEntry != null) IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
        },
        actions = { GetActions(currentBackStackEntry, navController) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.inverseSurface
        )
    )
}


@Composable
fun GetActions(
    currentBackStackEntry: NavBackStackEntry?,
    navController: NavHostController
) {
    val destination = currentBackStackEntry?.destination?.route
    return when (destination) {
        CleanArchitectureNavigation.HOME -> HomeActions(currentBackStackEntry, navController)
        CleanArchitectureNavigation.SETTING -> SettingActions(currentBackStackEntry, navController)
        CleanArchitectureNavigation.GITHUB -> GithubActions(currentBackStackEntry, navController)
        else -> {}
    }
}

fun getTitle(destination: String?): String {
    return when (destination) {
        CleanArchitectureNavigation.HOME -> "Home Screen"
        CleanArchitectureNavigation.SETTING -> "Setting Screen"
        CleanArchitectureNavigation.GITHUB -> "Github List"
        else -> "No Screen"
    }
}