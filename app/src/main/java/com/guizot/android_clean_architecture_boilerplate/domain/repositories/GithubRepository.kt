package com.guizot.android_clean_architecture_boilerplate.domain.repositories

import com.guizot.android_clean_architecture_boilerplate.domain.model.User

interface GithubRepository {
    suspend fun searchUser(parameter: Map<String, String>): Result<List<User>>

}