package com.guizot.android_clean_architecture_boilerplate.data.data_source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.IOException

class SettingsLocalDataSource(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val THEME  = stringPreferencesKey("theme_key")
        val ACCENT = stringPreferencesKey("accent_key")
        val FONT   = stringPreferencesKey("font_key")   // <-- new
    }

    // ---------------- THEME ----------------
    fun observeTheme(): Flow<AppTheme> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { p ->
                when (p[Keys.THEME]) {
                    AppTheme.LIGHT.name -> AppTheme.LIGHT
                    AppTheme.DARK.name  -> AppTheme.DARK
                    else                -> AppTheme.SYSTEM
                }
            }

    suspend fun setTheme(theme: AppTheme) {
        dataStore.edit { it[Keys.THEME] = theme.name }
    }

    suspend fun getTheme(): AppTheme =
        dataStore.data.map { p ->
            when (p[Keys.THEME]) {
                AppTheme.LIGHT.name -> AppTheme.LIGHT
                AppTheme.DARK.name  -> AppTheme.DARK
                else                -> AppTheme.SYSTEM
            }
        }.first()

    // ---------------- ACCENT ----------------
    fun observeAccent(): Flow<AppAccent> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { p ->
                when (p[Keys.ACCENT]) {
                    AppAccent.GREEN.name  -> AppAccent.GREEN
                    AppAccent.BLUE.name   -> AppAccent.BLUE
                    AppAccent.PURPLE.name -> AppAccent.PURPLE
                    AppAccent.AMBER.name  -> AppAccent.AMBER
                    AppAccent.RED.name    -> AppAccent.RED
                    else                  -> AppAccent.BLUE
                }
            }

    suspend fun setAccent(accent: AppAccent) {
        dataStore.edit { it[Keys.ACCENT] = accent.name }
    }

    suspend fun getAccent(): AppAccent =
        dataStore.data.map { p ->
            when (p[Keys.ACCENT]) {
                AppAccent.GREEN.name  -> AppAccent.GREEN
                AppAccent.BLUE.name   -> AppAccent.BLUE
                AppAccent.PURPLE.name -> AppAccent.PURPLE
                AppAccent.AMBER.name  -> AppAccent.AMBER
                AppAccent.RED.name    -> AppAccent.RED
                else                  -> AppAccent.BLUE
            }
        }.first()

    // ---------------- FONT (new) ----------------
    fun observeFont(): Flow<AppFont> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { p ->
                when (p[Keys.FONT]) {
                    AppFont.SANS_SERIF.name -> AppFont.SANS_SERIF
                    AppFont.SERIF.name      -> AppFont.SERIF
                    AppFont.MONOSPACE.name  -> AppFont.MONOSPACE
                    else                    -> AppFont.DEFAULT
                }
            }

    suspend fun setFont(font: AppFont) {
        dataStore.edit { it[Keys.FONT] = font.name }
    }

    suspend fun getFont(): AppFont =
        dataStore.data.map { p ->
            when (p[Keys.FONT]) {
                AppFont.SANS_SERIF.name -> AppFont.SANS_SERIF
                AppFont.SERIF.name      -> AppFont.SERIF
                AppFont.MONOSPACE.name  -> AppFont.MONOSPACE
                else                    -> AppFont.DEFAULT
            }
        }.first()
}