package com.guizot.android_clean_architecture_boilerplate.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guizot.android_clean_architecture_boilerplate.presentation.core.composeable.CustomAppBar


@Composable
fun SettingScreen(navController: NavHostController) {
    Scaffold(
        topBar = { CustomAppBar(navController) }
    ) { innerPadding ->
        val sampleItems = List(10) { index ->
            Item(id = index, title = "Title ${index+1}", body = "Body ${index+1}")
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            ItemList(messages = sampleItems) { message ->
                // Handle item click
            }
        }
    }
}

@Composable
fun CommonItem(
    message: Item,
    onClick: (Item) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(bottom = Dp(value = 16.0F))
            .clickable { onClick(message) }
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceDim,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = Dp(value = 16.0F))
    ) {
        Text(text = message.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = message.body, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ItemList(messages: List<Item>, onItemClick: (Item) -> Unit) {
    LazyColumn(
            contentPadding = PaddingValues( all = 16.dp),
        ) {
        items(messages) { message ->
            CommonItem(message = message, onClick = onItemClick)
        }
    }
}

data class Item(val id: Int, val title: String, val body: String)