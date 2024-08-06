package com.guizot.android_clean_architecture_boilerplate.presentation.pages

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.guizot.android_clean_architecture_boilerplate.ui.theme.AndroidCleanArchitectureBoilerplateTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    AndroidCleanArchitectureBoilerplateTheme {
        val navController = rememberNavController()
        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val previousBackStackEntry = navController.previousBackStackEntry
        val currentDestination = currentBackStackEntry?.destination?.route

        Scaffold(
            topBar = {
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
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = "home",
                Modifier.padding(innerPadding),
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                composable("home") { HomeScreen(navController) }
                composable("details") { DetailsScreen(navController) }
                composable("setting") { SettingScreen(navController) }
            }
        }
    }
}

@Composable
fun getTitleForDestination(destination: String?): String {
    return when (destination) {
        "home" -> "Home Screen"
        "details" -> "Details Screen"
        "setting" -> "Setting Screen"
        else -> ""
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Button(onClick = { navController.navigate("details") }) {
            Text(text = "Go to Details")
        }
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details Screen")
        Button(onClick = { navController.navigate("setting") }) {
            Text(text = "Go to Setting")
        }
    }
}

@Composable
fun SettingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Setting Screen")
    }
}