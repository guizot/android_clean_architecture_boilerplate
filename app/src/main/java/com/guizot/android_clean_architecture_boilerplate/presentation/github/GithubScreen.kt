package com.guizot.android_clean_architecture_boilerplate.presentation.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.guizot.android_clean_architecture_boilerplate.core.presentation.composable.CommonItem
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserUi

@Composable
fun GithubScreen(
    viewModel: GithubViewModel,
) {
    val userPagingItems: LazyPagingItems<UserUi> = viewModel.usersState.collectAsLazyPagingItems()

    userPagingItems.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> {
                Box (
                    modifier = Modifier.padding(16.dp),
                ) {
                    CommonItem(
                        child = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.inverseSurface
                                )
                            }
                        }
                    )
                }
            }

            is LoadState.Error -> {
                val error = userPagingItems.loadState.refresh as LoadState.Error
                Box (
                    modifier = Modifier.padding(16.dp),
                ) {
                    CommonItem(
                        child = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = error.error.message.toString())
                            }
                        }
                    )
                }
            }
            is LoadState.NotLoading -> Box( modifier = Modifier )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(userPagingItems.itemCount) { index ->
            CommonItem(
                title = userPagingItems[index]?.login.toString(),
                leading = {
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    ) {
                        AsyncImage(
                            model = userPagingItems[index]?.avatarUrl,
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(CircleShape)
                        )
                    }
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
                        text = userPagingItems[index]?.url.toString(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        userPagingItems.apply {
            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CommonItem(
                            child = {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.inverseSurface
                                    )
                                }
                            }
                        )
                    }
                }

                is LoadState.Error -> {
                    val error = userPagingItems.loadState.append as LoadState.Error
                    item {
                        CommonItem(
                            title = "Error",
                            child = {
                                Text(text = error.error.message.toString())
                            }
                        )
                    }
                }
                is LoadState.NotLoading -> item {}
            }
        }
    }

}