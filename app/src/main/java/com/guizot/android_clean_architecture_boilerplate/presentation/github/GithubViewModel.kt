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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    searchUserUseCase: SearchUserUseCase
) : ViewModel() {

    private val _usersPagingState: Flow<PagingData<UserUi>> = searchUserUseCase.invoke()
        .map { pagingData -> pagingData.map { it.toUi() } }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)
    val usersPagingState: Flow<PagingData<UserUi>> get() = _usersPagingState

    private val _navigation = Channel<GithubList.Navigation>()
    val navigation: Flow<GithubList.Navigation> = _navigation.receiveAsFlow()

    fun onEvent(event: GithubList.Event) {
        viewModelScope.launch {
            when (event) {
                is GithubList.Event.GoToDetail -> {
                    _navigation.send(GithubList.Navigation.GoToDetail(event.username))
                }
            }
        }
    }

}

object GithubList {

    sealed interface Navigation {
        data class GoToDetail(val username: String?) : Navigation
    }

    sealed interface Event {
        data class GoToDetail(val username: String?) : Event
    }

}
