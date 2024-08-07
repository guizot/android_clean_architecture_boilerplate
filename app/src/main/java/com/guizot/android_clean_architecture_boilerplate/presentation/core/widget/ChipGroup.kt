package com.guizot.android_clean_architecture_boilerplate.presentation.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.domain.model.ChipItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(chips: List<ChipItem>) {
    var selectedChipId by remember { mutableStateOf<Int?>(null) }

    FlowRow(
        modifier = Modifier
//            .background(color = Color.Red)
    ) {
        chips.forEach { chip ->
            FilterChip(
                selected = selectedChipId == chip.id,
                onClick = { selectedChipId = chip.id },
                label = { Text(chip.label) },
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}