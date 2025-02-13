package com.guizot.android_clean_architecture_boilerplate.presentation.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guizot.android_clean_architecture_boilerplate.core.data.NetworkResult
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toList
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toUi
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.DeleteUserUseCase
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.DetailUserUseCase
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.GetUserLocalUseCase
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.InsertUserUseCase
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserDetailUi
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserEntityUi
import com.guizot.android_clean_architecture_boilerplate.presentation.mappers.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubDetailViewModel @Inject constructor(
    private val detailUserUseCase: DetailUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getUserLocalUseCase: GetUserLocalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GithubDetail.UiState())
    val uiState: StateFlow<GithubDetail.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: GithubDetail.Event) {
        viewModelScope.launch {
            when (event) {
                is GithubDetail.Event.GetUser -> {
                    getUser(event.username)
                }
                is GithubDetail.Event.DeleteUser -> {
                    deleteUser(event.user)
                }
                is GithubDetail.Event.InsertUser -> {
                    insertUser(event.user)
                }
                is GithubDetail.Event.GetUserLocal -> {
                    getUserLocal(event.login)
                }
            }
        }
    }

    private fun getUser(id: String?) = detailUserUseCase.invoke(id.toString())
        .onEach { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    _uiState.update {
                        GithubDetail.UiState(
                            isLoading = true
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update {
                        GithubDetail.UiState(
                            isLoading = false,
                            error = result.message.toString()
                        )
                    }

                }
                is NetworkResult.Success -> {
                    result.data?.let { item ->
                        _uiState.update {
                            GithubDetail.UiState(
                                isLoading = false,
                                data = item.toUi(),
                                dataAsList = item.toList()
                            )
                        }
                        item.toUi().login?.let {
                            onEvent(GithubDetail.Event.GetUserLocal(it))
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

    private fun deleteUser(user: UserDetailUi) {
        deleteUserUseCase.invoke(user.toEntity())
            .launchIn(viewModelScope)
        user.login?.let {
            onEvent(GithubDetail.Event.GetUserLocal(it))
        }
    }

    private fun insertUser(user: UserDetailUi) {
        insertUserUseCase.invoke(user.toEntity())
            .launchIn(viewModelScope)
        user.login?.let {
            onEvent(GithubDetail.Event.GetUserLocal(it))
        }
    }

    private suspend fun getUserLocal(login: String) =
        viewModelScope.launch {
            getUserLocalUseCase.invoke(login).collectLatest { item ->
                _uiState.value = _uiState.value.copy (
                    isFavorite = item?.toUi()
                )
            }
        }

}

object GithubDetail {
    data class UiState(
        val isLoading: Boolean = false,
        val isFavorite: UserEntityUi? = null,
        val error: String = "",
        val data: UserDetailUi? = null,
        val dataAsList: List<Pair<String, Any?>>? = null
    )

    sealed interface Event {
        data class GetUser(val username: String?) : Event
        data class InsertUser(val user: UserDetailUi) : Event
        data class DeleteUser(val user: UserDetailUi) : Event
        data class GetUserLocal(val login: String) : Event

    }

}
