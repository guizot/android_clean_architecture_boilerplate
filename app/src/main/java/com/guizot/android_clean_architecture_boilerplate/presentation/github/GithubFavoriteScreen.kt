package com.guizot.android_clean_architecture_boilerplate.presentation.github

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import coil.compose.AsyncImage
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GithubFavoriteScreen(
    viewModel: GithubFavoriteViewModel,
    onClickItem: (username: String?) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = viewModel.navigation) {
        viewModel.navigation.flowWithLifecycle(lifecycleOwner.lifecycle)
            .collectLatest {
                when(it){
                    is GithubFavorite.Navigation.GoToDetail -> {
                        onClickItem(it.username)
                    }
                }
            }
    }

    uiState.value.data?.let { list ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(list) { item ->
                CommonItem(
                    title = item.login,
                    onClickItem = {
                        viewModel.onEvent(GithubFavorite.Event.GoToDetail(item.login))
                    },
                    leading = {
                        AsyncImage(
                            model = item.avatarUrl,
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape).height(70.dp).width(70.dp)
                        )
                    },
                    trailing = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            modifier = Modifier.scale(1.5F)
                        )
                    },
                    child = {
                        Text(
                            text = item.url,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


}