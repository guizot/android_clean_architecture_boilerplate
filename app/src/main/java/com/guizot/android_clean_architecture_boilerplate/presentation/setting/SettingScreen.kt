package com.guizot.android_clean_architecture_boilerplate.presentation.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.model.ChipItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.model.CommonItemModel
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.ChipGroup
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppTheme
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppFont
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentOption
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentSwatchRow

@Composable
fun SettingScreen(
    viewModel: SettingsViewModel
) {
    val state by viewModel.ui.collectAsState()

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

    // ----- Font chips mapping (NEW) -----
    fun toFontId(f: AppFont): Int = when (f) {
        AppFont.DEFAULT     -> 1
        AppFont.SANS_SERIF -> 2
        AppFont.SERIF      -> 3
        AppFont.MONOSPACE  -> 4
    }
    fun toFont(id: Int): AppFont = when (id) {
        2 -> AppFont.SANS_SERIF
        3 -> AppFont.SERIF
        4 -> AppFont.MONOSPACE
        else -> AppFont.DEFAULT
    }
    var selectedFontId by remember { mutableStateOf<Int?>(toFontId(state.selectedFont)) }
    LaunchedEffect(state.selectedFont) {
        selectedFontId = toFontId(state.selectedFont)
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
                    viewModel.onThemeSelected(toTheme(id))
                }
            }
        ),
        CommonItemModel(
            title = "Accent Color",
            child = {
                AccentSwatchRow(
                    selectedId = selectedAccentId,
                    enabled = !state.isSaving,
                    isDark = isDark
                ) { id ->
                    selectedAccentId = id
                    val chosen = AccentOption.defaultOptions().firstOrNull { it.id == id }?.value ?: AppAccent.BLUE
                    viewModel.onAccentSelected(chosen)
                }
            }
        ),
        // -------- NEW: App Font picker --------
        CommonItemModel(
            title = "App Font",
            child = {
                ChipGroup(
                    listOf(
                        ChipItem(1, "System"),
                        ChipItem(2, "Sans Serif"),
                        ChipItem(3, "Serif"),
                        ChipItem(4, "Monospace"),
                    ),
                    selectedFontId
                ) { id ->
                    selectedFontId = id
                    viewModel.onFontSelected(toFont(id))
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
//                        if (state.isSaving) {
//                            CircularProgressIndicator(
//                                modifier = Modifier
//                                    .align(Alignment.CenterEnd)
//                                    .size(18.dp),
//                                strokeWidth = 2.dp
//                            )
//                        }
                    }
                }
            )
        }
    }
}
