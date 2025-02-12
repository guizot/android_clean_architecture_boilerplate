package com.guizot.android_clean_architecture_boilerplate.presentation.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guizot.android_clean_architecture_boilerplate.core.data.NetworkResult
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toList
import com.guizot.android_clean_architecture_boilerplate.domain.mappers.toUi
import com.guizot.android_clean_architecture_boilerplate.domain.usecases.DetailUserUseCase
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserDetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubDetailViewModel @Inject constructor(
    private val detailUserUseCase: DetailUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GithubDetail.UiState())
    val uiState: StateFlow<GithubDetail.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: GithubDetail.Event) {
        viewModelScope.launch {
            when (event) {
                is GithubDetail.Event.GetUser -> {
                    getUser(event.username)
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
                        Log.d("JUST_LOG", item.toString())
                        _uiState.update {
                            GithubDetail.UiState(
                                isLoading = false,
                                data = item.toUi(),
                                dataAsList = item.toList()
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)

}

object GithubDetail {
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: UserDetailUi? = null,
        val dataAsList: List<Pair<String, Any?>>? = null
    )

    sealed interface Event {
        data class GetUser(val username: String?) : Event

    }

}
