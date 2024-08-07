package com.guizot.android_clean_architecture_boilerplate.presentation.core.widget

import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun getTitleForDestination(destination: String?): String {
    return when (destination) {
        "home" -> "Home Screen"
        "details" -> "Details Screen"
        "setting" -> "Setting Screen"
        else -> ""
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    navController: NavHostController,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val previousBackStackEntry = navController.previousBackStackEntry
    val currentDestination = currentBackStackEntry?.destination?.route

    TopAppBar(
        title = { Text(getTitleForDestination(currentDestination)) },
        navigationIcon = {
            if (previousBackStackEntry != null) IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.surface
        )
    )
}