package com.guizot.android_clean_architecture_boilerplate.presentation.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toUi
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.SearchUserUseCase
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GithubList.UiState())
    val uiState: StateFlow<GithubList.UiState> get() = _uiState.asStateFlow()

    private val _usersPagingState: MutableStateFlow<PagingData<UserUi>> = MutableStateFlow(value = PagingData.empty())
    val usersPagingState: MutableStateFlow<PagingData<UserUi>> get() = _usersPagingState

    private val _navigation = Channel<GithubList.Navigation>()
    val navigation: Flow<GithubList.Navigation> = _navigation.receiveAsFlow()

    init {
        onEvent(GithubList.Event.SearchUser)
    }

    fun onEvent(event: GithubList.Event) {
        viewModelScope.launch {
            when (event) {
                is GithubList.Event.SearchUser -> {
                    searchUser()
                }
                is GithubList.Event.GoToDetail -> {
                    _navigation.send(GithubList.Navigation.GoToDetail(event.username))
                }
            }
        }
    }

    private suspend fun searchUser() {
        searchUserUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _usersPagingState.value = it.map { item -> item.toUi() }
            }
    }

//    private fun searchUser() = searchUserUseCase.invoke()
//        .onEach { result ->
//            when (result) {
//                is NetworkResult.Loading -> {
//                    _uiState.update {
//                        GithubList.UiState(
//                            isLoading = true
//                        )
//                    }
//                }
//
//                is NetworkResult.Error -> {
//                    _uiState.update {
//                        GithubList.UiState(
//                            isLoading = false,
//                            error = result.message.toString()
//                        )
//                    }
//
//                }
//
//                is NetworkResult.Success -> {
//                    result.data?.let { list ->
//                        _uiState.update {
//                            GithubList.UiState(
//                                isLoading = false,
//                                data = list.toUi()
//                            )
//                        }
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)

}

object GithubList {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<UserUi>? = null
    )

    sealed interface Navigation {
        data class GoToDetail(val username: String?) : Navigation
    }

    sealed interface Event {
        data object SearchUser : Event
        data class GoToDetail(val username: String?) : Event
    }

}
