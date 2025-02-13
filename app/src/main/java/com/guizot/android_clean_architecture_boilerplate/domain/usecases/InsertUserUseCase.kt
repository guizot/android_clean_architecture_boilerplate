package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import com.guizot.android_clean_architecture_boilerplate.presentation.github.model.UserEntityUi
import com.guizot.android_clean_architecture_boilerplate.presentation.mappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    operator fun invoke(user: UserEntityUi) = flow<Unit> {
        githubRepository.insertUser(user.toDomain())
    }.flowOn(Dispatchers.IO)

}