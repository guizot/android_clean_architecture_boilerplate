package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AndroidCleanArchitectureBoilerplateTheme
import androidx.navigation.compose.rememberNavController
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.ThemeSettingsUseCase
import androidx.compose.runtime.getValue
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent

@Composable
fun CleanArchitectureApp(
    themeUC: ThemeSettingsUseCase
) {
    val appTheme by themeUC.observeTheme().collectAsState(initial = AppTheme.SYSTEM)
    val appAccent by themeUC.observeAccent().collectAsState(initial = AppAccent.BLUE)

    AndroidCleanArchitectureBoilerplateTheme(appTheme = appTheme, appAccent = appAccent) {
        val navHostController = rememberNavController()
        Scaffold(
            topBar = { CleanArchitectureAppBar(navHostController) }
        ) { innerPadding ->
            CleanArchitectureNavigation(
                navHostController,
                Modifier.fillMaxSize().padding(innerPadding)
            )
        }
    }
}