package com.guizot.android_clean_architecture_boilerplate.presentation.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

    FlowRow (
        // modifier = Modifier.background(color = Color.Red),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        maxItemsInEachRow = chips.size
    ) {
        chips.forEach { chip ->
            FilterChip(
                selected = selectedChipId == chip.id,
                onClick = { selectedChipId = chip.id },
                label = { Text(chip.label) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.surface,
                ),
                leadingIcon = if (selectedChipId == chip.id) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.surface
                        )
                    }
                } else null,
                shape = RoundedCornerShape(50),
            )
        }
    }
}