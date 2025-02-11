package com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guizot.android_clean_architecture_boilerplate.presentation.setting.model.ChipItem

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipGroup(
    chips: List<ChipItem>,
    selectedChipId: Int?,
    onChipSelected: (Int) -> Unit
) {

    FlowRow (
        // modifier = Modifier.background(color = Color.Red),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = chips.size
    ) {
        chips.forEach { chip ->
            FilterChip(
                selected = selectedChipId == chip.id,
                onClick = {
                    onChipSelected(chip.id)
                },
                label = {
                    Text(
                        chip.label,
                        // fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                leadingIcon = if (selectedChipId == chip.id) {
                    {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                } else null,
                shape = RoundedCornerShape(50),
            )
        }
    }
}