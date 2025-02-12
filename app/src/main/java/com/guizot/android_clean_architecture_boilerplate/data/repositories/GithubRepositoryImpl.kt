package com.guizot.android_clean_architecture_boilerplate.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guizot.android_clean_architecture_boilerplate.data.data_source.remote.GithubApiService
import com.guizot.android_clean_architecture_boilerplate.data.mappers.toDomain
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserDetail
import com.guizot.android_clean_architecture_boilerplate.domain.paging.GithubPagingSource
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val githubApiService: GithubApiService,
) : GithubRepository {

    override suspend fun searchUser(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 1000, prefetchDistance = 2),
            pagingSourceFactory = {
                GithubPagingSource(githubApiService)
            }
        ).flow
    }

    override suspend fun detailUser(username: String): Result<UserDetail> {
        return try {
            val response = githubApiService.detailUser(username = username)
            if (response.isSuccessful) {
                response.body()?.let {
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