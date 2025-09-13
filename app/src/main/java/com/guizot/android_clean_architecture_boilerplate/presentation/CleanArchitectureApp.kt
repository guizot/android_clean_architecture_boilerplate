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
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont

@Composable
fun CleanArchitectureApp(
    themeSettingsUseCase: ThemeSettingsUseCase
) {
    val appTheme by themeSettingsUseCase.observeTheme().collectAsState(initial = AppTheme.SYSTEM)
    val appAccent by themeSettingsUseCase.observeAccent().collectAsState(initial = AppAccent.BLUE)
    val appFont  by themeSettingsUseCase.observeFont().collectAsState(initial = AppFont.DEFAULT) // NEW

    AndroidCleanArchitectureBoilerplateTheme(
        appTheme = appTheme,
        appAccent = appAccent,
        appFont = appFont,                 // NEW
    ) {
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