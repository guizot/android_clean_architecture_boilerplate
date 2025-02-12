package com.guizot.android_clean_architecture_boilerplate.domain.repositories

import androidx.paging.PagingData
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserDetail
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchUser(): Flow<PagingData<User>>
    suspend fun detailUser(username: String): Result<UserDetail>
}