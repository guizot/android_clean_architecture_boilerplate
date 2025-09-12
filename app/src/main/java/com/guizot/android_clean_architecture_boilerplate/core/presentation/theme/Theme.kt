package com.guizot.android_clean_architecture_boilerplate.core.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable.AccentOption


@Composable
fun AndroidCleanArchitectureBoilerplateTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    appAccent: AppAccent = AppAccent.BLUE,
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
        isDark -> darkColorScheme( primary = colorAccent.darkColor )
        else   -> lightColorScheme( primary = colorAccent.lightColor )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
