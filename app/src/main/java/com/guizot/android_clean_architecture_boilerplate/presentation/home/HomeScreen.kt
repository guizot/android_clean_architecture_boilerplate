package com.guizot.android_clean_architecture_boilerplate.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem

@Composable
fun HomeScreen(
    onClickGithub: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CommonItem(
            title = "Github List",
            icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onClickItem = onClickGithub,
            child = {
                Text(text = "list of favorite github users using retrofit, room & hilt")
            }
        )
    }
}