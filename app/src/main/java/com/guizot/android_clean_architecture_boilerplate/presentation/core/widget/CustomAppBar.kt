package com.guizot.android_clean_architecture_boilerplate.presentation.core.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    navController: NavHostController,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val previousBackStackEntry = navController.previousBackStackEntry
    val currentDestination = currentBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            Text(
                getTitleForDestination(currentDestination),
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
        actions = {
            GetActionForDestination(currentDestination, navController)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.inverseSurface
        )
    )
}
@Composable
fun GetActionForDestination(destination: String?, navController: NavHostController) {
    return when (destination) {
        "home" -> {
            IconButton(
                onClick = {
                    navController.navigate("setting")
//                    navController.popBackStack()
//                    navController.navigate("setting") {
//                        Log.d("GRAPH_ID", navController.graph.route.toString())
//                        popUpTo(
//                            navController.graph.id
//                        ) {
//                            inclusive = true
//                        }
//                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Setting",
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
        else -> {}
    }
}

fun getTitleForDestination(destination: String?): String {
    return when (destination) {
        "home" -> "Home Screen"
        "details" -> "Details Screen"
        "setting" -> "Setting Screen"
        else -> ""
    }
}