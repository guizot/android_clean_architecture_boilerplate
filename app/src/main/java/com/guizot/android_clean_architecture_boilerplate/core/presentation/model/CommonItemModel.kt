package com.guizot.android_clean_architecture_boilerplate.core.presentation.model

import androidx.compose.runtime.Composable

data class CommonItemModel(
    val title: String,
    val child: @Composable () -> Unit = {}
)