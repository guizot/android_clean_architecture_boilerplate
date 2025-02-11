package com.guizot.android_clean_architecture_boilerplate.domain.repositories

import androidx.paging.PagingData
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchUser(): Flow<PagingData<User>>

}