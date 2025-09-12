package com.guizot.android_clean_architecture_boilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.ThemeSettingsUseCase
import com.guizot.android_clean_architecture_boilerplate.presentation.CleanArchitectureApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var themeSettingsUseCase: ThemeSettingsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureApp(themeSettingsUseCase)
        }
    }
}

