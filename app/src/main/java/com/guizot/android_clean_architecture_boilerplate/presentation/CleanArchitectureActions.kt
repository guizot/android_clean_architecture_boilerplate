package com.guizot.android_clean_architecture_boilerplate.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubDetail
import com.guizot.android_clean_architecture_boilerplate.presentation.github.GithubDetailViewModel

@Composable
fun HomeActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    IconButton(
        onClick = {
            navController.navigate(Setting)
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Setting",
            tint = MaterialTheme.colorScheme.inverseSurface
        )
    }
}

@Composable
fun SettingActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {

}

@Composable
fun GithubActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    IconButton(
        onClick = {
            navController.navigate(GithubFavorite)
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.List,
            contentDescription = "Setting",
            tint = MaterialTheme.colorScheme.inverseSurface
        )
    }
}

@Composable
fun GithubDetailActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {
    val viewModel = hiltViewModel<GithubDetailViewModel>(backStackEntry)
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.data?.let { item ->
        IconButton(
            onClick = {
                if (uiState.value.isFavorite != null) {
                    viewModel.onEvent(GithubDetail.Event.DeleteUser(item))
                } else {
                    viewModel.onEvent(GithubDetail.Event.InsertUser(item))
                }
            }
        ) {
            Icon(
                imageVector = if (uiState.value.isFavorite != null) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Setting",
                tint = MaterialTheme.colorScheme.inverseSurface,
            )
        }
    }
}

@Composable
fun GithubFavoriteActions(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController
) {

}