package com.guizot.android_clean_architecture_boilerplate.data.repositories

import com.guizot.android_clean_architecture_boilerplate.data.data_source.remote.GithubApiService
import com.guizot.android_clean_architecture_boilerplate.data.mappers.toDomain
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository

class GithubRepositoryImpl(
    private val githubApiService: GithubApiService,
) : GithubRepository {
    override suspend fun searchUser(parameter: Map<String, String>): Result<List<User>> {
        return try {
            val response = githubApiService.searchUser(parameter)
            if (response.isSuccessful) {
                response.body()?.items?.let {
                    Result.success(it.toDomain())
                } ?: run { Result.failure(Exception("error occurred")) }
            } else {
                Result.failure(Exception("error occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}