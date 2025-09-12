package com.guizot.android_clean_architecture_boilerplate.presentation.setting.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.AppAccent
import com.guizot.android_clean_architecture_boilerplate.core.presentation.theme.*

/**
 * A row of circular color swatches. The selected one shows a check mark.
 * Matches your “no shimmer” approach; optionally pass enabled=false to block taps while saving.
 */
@Composable
fun AccentSwatchRow(
    selectedId: Int?,
    enabled: Boolean = true,
    isDark: Boolean,
    onSelected: (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AccentOption.defaultOptions().forEach { opt ->
            val isSelected = selectedId == opt.id
            val swatchColor = if (isDark) opt.darkColor else opt.lightColor

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(swatchColor)
                    .clickable(
                        enabled = enabled,
                        role = Role.Button,
                        onClick = { onSelected(opt.id) }
                    )
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "${opt.label} selected",
                        tint = if (isDark) Color.White else Color.Black
                    )
                }
            }
        }
    }
}

data class AccentOption(
    val id: Int,
    val label: String,
    val lightColor: Color,
    val darkColor: Color,
    val value: AppAccent
) {
    companion object {
        fun defaultOptions(): List<AccentOption> = listOf(
            AccentOption(1, "Green",  Green80,  Green40,  AppAccent.GREEN),
            AccentOption(2, "Blue",   Blue80,   Blue40,   AppAccent.BLUE),
            AccentOption(3, "Purple", Purple80, Purple40, AppAccent.PURPLE),
            AccentOption(4, "Amber",  Amber80,  Amber40,  AppAccent.AMBER),
            AccentOption(5, "Red",    Red80,    Red40,    AppAccent.RED),
        )
    }
}