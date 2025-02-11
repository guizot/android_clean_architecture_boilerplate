package com.guizot.android_clean_architecture_boilerplate.presentation.pages.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem

@Composable
fun GithubScreen(
    viewModel: GithubViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    if (uiState.value.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    if (uiState.value.error != "") {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = uiState.value.error)
        }
    }

    uiState.value.data?.let { list ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            ) {
            items(list) {
                CommonItem(
                    title = it.login.toString(),
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    child = {
                        Text(
                            text = it.url.toString(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }

}