package com.guizot.android_clean_architecture_boilerplate.presentation.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toUi
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.GetAllUserLocalUseCase
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.SearchUserUseCase
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserDetailUi
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserEntityUi
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubFavoriteViewModel @Inject constructor(
    private val getAllUserLocalUseCase: GetAllUserLocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GithubFavorite.UiState())
    val uiState: StateFlow<GithubFavorite.UiState> get() = _uiState.asStateFlow()

    private val _navigation = Channel<GithubFavorite.Navigation>()
    val navigation: Flow<GithubFavorite.Navigation> = _navigation.receiveAsFlow()

    init {
        onEvent(GithubFavorite.Event.GetUser)
    }

    fun onEvent(event: GithubFavorite.Event) {
        viewModelScope.launch {
            when (event) {
                is GithubFavorite.Event.GetUser -> {
                    getAllUserLocal()
                }
                is GithubFavorite.Event.GoToDetail -> {
                    _navigation.send(GithubFavorite.Navigation.GoToDetail(event.username))
                }
            }
        }
    }

    private suspend fun getAllUserLocal() =
        viewModelScope.launch {
            getAllUserLocalUseCase.invoke().collectLatest { list ->
                _uiState.update {
                    GithubFavorite.UiState(data = list.map { it.toUi() })
                }
            }
        }

}

object GithubFavorite {

    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<UserEntityUi>? = null,
    )
    sealed interface Navigation {
        data class GoToDetail(val username: String?) : Navigation
    }

    sealed interface Event {
        data object GetUser : Event
        data class GoToDetail(val username: String?) : Event
    }

}
