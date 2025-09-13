package com.guizot.android_clean_architecture_boilerplate.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun typographyFor(defaultFamily: FontFamily): Typography {
    val base = Typography()

    return Typography(
        displayLarge = base.displayLarge.copy(fontFamily = defaultFamily),
        displayMedium = base.displayMedium.copy(fontFamily = defaultFamily),
        displaySmall = base.displaySmall.copy(fontFamily = defaultFamily),

        headlineLarge = base.headlineLarge.copy(fontFamily = defaultFamily),
        headlineMedium = base.headlineMedium.copy(fontFamily = defaultFamily),
        headlineSmall = base.headlineSmall.copy(fontFamily = defaultFamily),

        titleLarge = base.titleLarge.copy(fontFamily = defaultFamily),
        titleMedium = base.titleMedium.copy(fontFamily = defaultFamily),
        titleSmall = base.titleSmall.copy(fontFamily = defaultFamily),

        bodyLarge = base.bodyLarge.copy(fontFamily = defaultFamily),
        bodyMedium = base.bodyMedium.copy(fontFamily = defaultFamily),
        bodySmall = base.bodySmall.copy(fontFamily = defaultFamily),

        labelLarge = base.labelLarge.copy(fontFamily = defaultFamily),
        labelMedium = base.labelMedium.copy(fontFamily = defaultFamily),
        labelSmall = base.labelSmall.copy(fontFamily = defaultFamily),
    )
}