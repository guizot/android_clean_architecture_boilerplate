package com.guizot.android_clean_architecture_boilerplate.core.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentOption

@Composable
fun AndroidCleanArchitectureBoilerplateTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    appAccent: AppAccent = AppAccent.BLUE,
    appFont: AppFont = AppFont.DEFAULT,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorAccent = AccentOption.defaultOptions().first { it.value == appAccent }

    val isDark = when (appTheme) {
        AppTheme.LIGHT  -> false
        AppTheme.DARK   -> true
        AppTheme.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDark -> darkColorScheme(primary = colorAccent.darkColor)
        else   -> lightColorScheme(primary = colorAccent.lightColor)
    }

    // Map AppFont -> FontFamily
    val fontFamily = remember(appFont) {
        when (appFont) {
            AppFont.DEFAULT     -> FontFamily.Default
            AppFont.SANS_SERIF -> FontFamily.SansSerif
            AppFont.SERIF      -> FontFamily.Serif
            AppFont.MONOSPACE  -> FontFamily.Monospace
        }
    }

    val typography = remember(fontFamily) { typographyFor(fontFamily) } // uses Type.kt factory

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}