package com.guizot.android_clean_architecture_boilerplate.domain.repositories

import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    // Theme
    fun observeTheme(): Flow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
    suspend fun getTheme(): AppTheme

    // Accent
    fun observeAccent(): Flow<AppAccent>
    suspend fun setAccent(accent: AppAccent)
    suspend fun getAccent(): AppAccent
}