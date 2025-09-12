package com.guizot.android_clean_architecture_boilerplate.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.ThemeSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeSettingsUseCase: ThemeSettingsUseCase
) : ViewModel() {

    private val _ui = MutableStateFlow(SettingsUiState())
    val ui = _ui.asStateFlow()

    init {
        // Observe theme
        viewModelScope.launch {
            themeSettingsUseCase.observeTheme().collect { t ->
                _ui.update { it.copy(selectedTheme = t) }
            }
        }
        // Observe accent
        viewModelScope.launch {
            themeSettingsUseCase.observeAccent().collect { a ->
                _ui.update { it.copy(selectedAccent = a) }
            }
        }
    }

    fun onThemeSelected(theme: AppTheme) {
        if (theme == _ui.value.selectedTheme) return
        viewModelScope.launch {
            _ui.update { it.copy(isSaving = true) }
            themeSettingsUseCase.setTheme(theme)
            _ui.update { it.copy(isSaving = false) }
        }
    }

    fun onAccentSelected(accent: AppAccent) {
        if (accent == _ui.value.selectedAccent) return
        viewModelScope.launch {
            _ui.update { it.copy(isSaving = true) }
            themeSettingsUseCase.setAccent(accent)
            _ui.update { it.copy(isSaving = false) }
        }
    }
}