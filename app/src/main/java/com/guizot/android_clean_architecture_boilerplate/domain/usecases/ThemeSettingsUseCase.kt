package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class ThemeSettingsUseCase(
    private val repo: SettingsRepository
) {
    // -------- THEME --------
    fun observeTheme(): Flow<AppTheme> = repo.observeTheme()
    suspend fun setTheme(theme: AppTheme) = repo.setTheme(theme)
    suspend fun getTheme(): AppTheme = repo.getTheme()

    // -------- ACCENT --------
    fun observeAccent(): Flow<AppAccent> = repo.observeAccent()
    suspend fun setAccent(accent: AppAccent) = repo.setAccent(accent)
    suspend fun getAccent(): AppAccent = repo.getAccent()

    // -------- FONT --------
    fun observeFont(): Flow<AppFont> = repo.observeFont()
    suspend fun setFont(font: AppFont) = repo.setFont(font)
    suspend fun getFont(): AppFont = repo.getFont()
}