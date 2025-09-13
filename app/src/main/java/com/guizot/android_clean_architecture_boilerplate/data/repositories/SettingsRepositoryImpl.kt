package com.guizot.android_clean_architecture_boilerplate.data.repositories

import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont
import com.guizot.android_clean_architecture_boilerplate.data.data_source.local.SettingsLocalDataSource
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val local: SettingsLocalDataSource
) : SettingsRepository {

    // ---------------- Theme ----------------
    override fun observeTheme(): Flow<AppTheme> = local.observeTheme()
    override suspend fun setTheme(theme: AppTheme) = local.setTheme(theme)
    override suspend fun getTheme(): AppTheme = local.getTheme()

    // ---------------- Accent ----------------
    override fun observeAccent(): Flow<AppAccent> = local.observeAccent()
    override suspend fun setAccent(accent: AppAccent) = local.setAccent(accent)
    override suspend fun getAccent(): AppAccent = local.getAccent()

    // ---------------- Font (new) ----------------
    override fun observeFont(): Flow<AppFont> = local.observeFont()
    override suspend fun setFont(font: AppFont) = local.setFont(font)
    override suspend fun getFont(): AppFont = local.getFont()
}
