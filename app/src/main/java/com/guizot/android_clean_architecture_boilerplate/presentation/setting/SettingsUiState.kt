package com.guizot.android_clean_architecture_boilerplate.presentation.setting

import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont

data class SettingsUiState(
    val selectedTheme: AppTheme = AppTheme.SYSTEM,
    val selectedAccent: AppAccent = AppAccent.BLUE,
    val selectedFont: AppFont = AppFont.DEFAULT,
    val isSaving: Boolean = false
)