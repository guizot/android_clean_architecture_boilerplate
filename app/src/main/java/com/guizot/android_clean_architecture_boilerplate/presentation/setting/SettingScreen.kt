package com.guizot.android_clean_architecture_boilerplate.presentation.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.model.ChipItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.model.CommonItemModel
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.ChipGroup
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentOption
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentSwatchRow

@Composable
fun SettingScreen(
    vm: SettingsViewModel = hiltViewModel()
) {
    val state by vm.ui.collectAsState()

    // ----- Theme chips mapping -----
    fun toChipId(t: AppTheme): Int = when (t) {
        AppTheme.LIGHT -> 1
        AppTheme.DARK  -> 2
        AppTheme.SYSTEM -> 3
    }
    fun toTheme(id: Int): AppTheme = when (id) {
        1 -> AppTheme.LIGHT
        2 -> AppTheme.DARK
        else -> AppTheme.SYSTEM
    }
    val systemDark = isSystemInDarkTheme()
    val isDark = when (state.selectedTheme) {
        AppTheme.LIGHT  -> false
        AppTheme.DARK   -> true
        AppTheme.SYSTEM -> systemDark
    }

    var selectedThemeChipId by remember { mutableStateOf<Int?>(toChipId(state.selectedTheme)) }
    LaunchedEffect(state.selectedTheme) {
        selectedThemeChipId = toChipId(state.selectedTheme)
    }

    // ----- Accent swatches mapping -----

    fun toAccentId(a: AppAccent): Int = when (a) {
        AppAccent.GREEN  -> 1
        AppAccent.BLUE   -> 2
        AppAccent.PURPLE -> 3
        AppAccent.AMBER  -> 4
        AppAccent.RED    -> 5
    }

    var selectedAccentId by remember { mutableStateOf<Int?>(toAccentId(state.selectedAccent)) }
    LaunchedEffect(state.selectedAccent) {
        selectedAccentId = toAccentId(state.selectedAccent)
    }

    // ----- Items -----
    val items = listOf(
        CommonItemModel(
            title = "Theme Mode",
            child = {
                ChipGroup(
                    listOf(
                        ChipItem(1, "Light"),
                        ChipItem(2, "Dark"),
                        ChipItem(3, "System"),
                    ),
                    selectedThemeChipId
                ) { id ->
                    selectedThemeChipId = id
                    vm.onThemeSelected(toTheme(id))
                }
            }
        ),
        CommonItemModel(
            title = "Accent Color",
            child = {
                // Follow your loading behavior: render the row but keep selection empty until loaded.
                AccentSwatchRow(
                    selectedId = if (state.isSaving) selectedAccentId else selectedAccentId, // same behavior; you can disable clicks during saving
                    enabled = !state.isSaving,
                    isDark = isDark
                ) { id ->
                    selectedAccentId = id
                    val chosen = AccentOption.defaultOptions().firstOrNull { it.id == id }?.value ?: AppAccent.BLUE
                    vm.onAccentSelected(chosen)
                }
            }
        ),
    )

    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items.size) { i ->
            val item = items[i]
            CommonItem(
                title = item.title,
                child = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        item.child()
                        if (state.isSaving) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(18.dp),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            )
        }
    }
}
