package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import javax.inject.Inject

class GetAllUserLocalUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    operator fun invoke() = githubRepository.getAllUsers()

}