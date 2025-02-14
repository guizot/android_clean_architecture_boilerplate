package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AndroidCleanArchitectureBoilerplateTheme
import androidx.navigation.compose.rememberNavController

@Composable
fun CleanArchitectureApp() {
    AndroidCleanArchitectureBoilerplateTheme {
        val navHostController = rememberNavController()
        Scaffold(
            topBar = {
                CleanArchitectureAppBar(navHostController)
            }
        ) { innerPadding ->
            CleanArchitectureNavigation(
                navHostController,
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}