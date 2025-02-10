package com.guizot.android_clean_architecture_boilerplate.presentation.pages.home

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.model.CommonItemModel
import com.guizot.android_clean_architecture_boilerplate.presentation.model.ChipItem
import com.guizot.android_clean_architecture_boilerplate.presentation.pages.setting.composable.ChipGroup

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