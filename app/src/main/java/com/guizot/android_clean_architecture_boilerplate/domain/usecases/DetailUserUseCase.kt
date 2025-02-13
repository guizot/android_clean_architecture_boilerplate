package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import com.guizot.android_clean_architecture_boilerplate.core.data.NetworkResult
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailUserUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    operator fun invoke(username: String) = flow {
        emit(NetworkResult.Loading())
        val response = githubRepository.detailUser(username)
        if (response.isSuccess) {
            emit(NetworkResult.Success(data = response.getOrThrow()))
        } else {
            emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}