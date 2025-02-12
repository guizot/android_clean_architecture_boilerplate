package com.guizot.android_clean_architecture_boilerplate.presentation.github

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import com.guizot.android_clean_architecture_boilerplate.core.presentation.utils.toReadableFormat

@Composable
fun GithubDetailScreen(
    viewModel: GithubDetailViewModel,
    username: String?,
) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = username) {
        username?.let { user ->
            viewModel.onEvent(GithubDetail.Event.GetUser(user))
        }
    }

    if(uiState.value.isLoading) {
        Box (
            modifier = Modifier.padding(16.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
    }

    if(uiState.value.error != "") {
        Box (
            modifier = Modifier.padding(16.dp),
        ) {
            CommonItem(
                child = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.value.error)
                    }
                }
            )
        }
    }

    uiState.value.dataAsList?.let { list ->
        LazyColumn(
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            items(list.size) {
                if(list[it].first == "avatarUrl") {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AsyncImage(
                            model = list[it].second.toString(),
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape).height(200.dp).width(200.dp)
                        )
                    }
                } else {
                    CommonItem(
                        title = list[it].first.toReadableFormat(),
                    ) {
                        Text(text = list[it].second.toString())
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}