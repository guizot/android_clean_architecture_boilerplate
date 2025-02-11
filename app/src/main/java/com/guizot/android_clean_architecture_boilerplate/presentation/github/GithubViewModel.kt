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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserList.UiState())
    val uiState: StateFlow<UserList.UiState> get() = _uiState.asStateFlow()

    private val _usersState: MutableStateFlow<PagingData<UserUi>> = MutableStateFlow(value = PagingData.empty())
    val usersState: MutableStateFlow<PagingData<UserUi>> get() = _usersState


    init {
        onEvent(UserList.Event.SearchUser)
    }

    fun onEvent(event: UserList.Event) {
        viewModelScope.launch {
            when (event) {
                is UserList.Event.SearchUser -> {
                    searchUser()
                }
            }
        }
    }

    private suspend fun searchUser() {
        searchUserUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _usersState.value = it.map { item -> item.toUi() }
            }
    }

//    private fun searchUser() = searchUserUseCase.invoke()
//        .onEach { result ->
//            when (result) {
//                is NetworkResult.Loading -> {
//                    _uiState.update {
//                        UserList.UiState(
//                            isLoading = true
//                        )
//                    }
//                }
//
//                is NetworkResult.Error -> {
//                    _uiState.update {
//                        UserList.UiState(
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
//                            UserList.UiState(
//                                isLoading = false,
//                                data = list.toUi()
//                            )
//                        }
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)

}

object UserList {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<UserUi>? = null
    )

    sealed interface Event {
        data object SearchUser : Event
    }

}
