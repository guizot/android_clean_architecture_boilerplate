package com.guizot.android_clean_architecture_boilerplate.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.guizot.android_clean_architecture_boilerplate.data.data_source.local.SettingsLocalDataSource
import com.guizot.android_clean_architecture_boilerplate.data.repositories.SettingsRepositoryImpl
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.SettingsRepository
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.ThemeSettingsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DS_NAME = "app_settings"
private val Context.settingsDataStore by preferencesDataStore(DS_NAME)

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides @Singleton
    fun provideDataStore(@ApplicationContext ctx: Context): DataStore<Preferences> =
        ctx.settingsDataStore

    @Provides @Singleton
    fun provideLocal(ds: DataStore<Preferences>) = SettingsLocalDataSource(ds)

    @Provides @Singleton
    fun provideRepo(local: SettingsLocalDataSource): SettingsRepository =
        SettingsRepositoryImpl(local)

    @Provides @Singleton
    fun provideThemeSettingsUseCase(repo: SettingsRepository) =
        ThemeSettingsUseCase(repo)
}
