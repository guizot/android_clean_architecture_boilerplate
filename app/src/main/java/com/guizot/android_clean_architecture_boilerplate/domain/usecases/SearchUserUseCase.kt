package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import androidx.paging.PagingData
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(private val githubRepository: GithubRepository) {

    suspend fun invoke(): Flow<PagingData<User>> {
        return githubRepository.searchUser()
    }

    //    operator fun invoke() = flow {
    //        emit(NetworkResult.Loading())
    //        val response = githubRepository.searchUser()
    //        if (response.isSuccess) {
    //            emit(NetworkResult.Success(data = response.getOrThrow()))
    //        } else {
    //            emit(NetworkResult.Error(message = response.exceptionOrNull()?.localizedMessage))
    //        }
    //    }.catch {
    //        emit(NetworkResult.Error(it.message.toString()))
    //    }.flowOn(Dispatchers.IO)

}