package com.guizot.android_clean_architecture_boilerplate.presentation.pages

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.domain.model.ChipItem
import com.guizot.android_clean_architecture_boilerplate.domain.model.CommonItemModel
import com.guizot.android_clean_architecture_boilerplate.presentation.core.widget.ChipGroup
import com.guizot.android_clean_architecture_boilerplate.presentation.core.widget.CommonItem

@Composable
fun SettingScreen() {
    var selectedChipId by remember { mutableStateOf<Int?>(2) }
    val settingItem = arrayOf(
        CommonItemModel(
            title = "Theme Mode",
            child = {
                ChipGroup(
                    listOf(
                        ChipItem(1, "Light"),
                        ChipItem(2, "Dark"),
                        ChipItem(3, "System"),
                    ),
                    selectedChipId
                ) {
                    selectedChipId = it
                }
            }
        ),
    )
    LazyColumn(
        contentPadding = PaddingValues( all = 16.dp),
    ) {
        items(settingItem) { item ->
            CommonItem(
                title = item.title,
                child = item.child
            )
        }
    }
}