package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun HomeActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    IconButton(
        onClick = {
            navController.navigate(CleanArchitectureNavigation.SETTING)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Setting",
            tint = MaterialTheme.colorScheme.inverseSurface
        )
    }
}

@Composable
fun SettingActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {

}

@Composable
fun GithubActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {

}