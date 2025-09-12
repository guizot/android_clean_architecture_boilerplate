package com.guizot.android_clean_architecture_boilerplate.presentation.setting

import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent

data class SettingsUiState(
    val selectedTheme: AppTheme = AppTheme.SYSTEM,
    val selectedAccent: AppAccent = AppAccent.BLUE,
    val isSaving: Boolean = false
)