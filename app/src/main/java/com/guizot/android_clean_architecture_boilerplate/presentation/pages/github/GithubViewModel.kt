package com.guizot.android_clean_architecture_boilerplate.presentation.pages.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guizot.android_clean_architecture_boilerplate.core.data.NetworkResult
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.SearchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserList.UiState())
    val uiState: StateFlow<UserList.UiState> get() = _uiState.asStateFlow()

    init {
        onEvent(UserList.Event.SearchUser)
    }

    fun onEvent(event: UserList.Event) {
        when (event) {
            is UserList.Event.SearchUser -> {
                searchUser()
            }
        }
    }

    private fun searchUser() = searchUserUseCase.invoke(
            mapOf(
                "q" to "followers:>10000",
                "per_page" to "10",
                "page" to "1"
            )
        )
        .onEach { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    _uiState.update {
                        UserList.UiState(
                            isLoading = true
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _uiState.update {
                        UserList.UiState(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }

                }

                is NetworkResult.Success -> {
                    _uiState.update {
                        UserList.UiState(
                            isLoading = false,
                            data = result.data
                        )
                    }

                }
            }
        }.launchIn(viewModelScope)

}

object UserList {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: List<User>? = null
    )

    sealed interface Event {
        data object SearchUser : Event
    }

}
