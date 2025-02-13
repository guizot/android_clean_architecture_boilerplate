package com.guizot.android_clean_architecture_boilerplate.domain.usecases

import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import javax.inject.Inject

class GetUserLocalUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    operator fun invoke(login: String) = githubRepository.getUser(login)

}