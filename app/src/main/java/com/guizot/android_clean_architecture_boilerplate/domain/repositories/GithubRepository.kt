package com.guizot.android_clean_architecture_boilerplate.domain.repositories

import androidx.paging.PagingData
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserDetail
import com.guizot.android_clean_architecture_boilerplate.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    // REMOTE
    suspend fun searchUser(): Flow<PagingData<User>>
    suspend fun detailUser(username: String): Result<UserDetail>

    // LOCAL
    suspend fun insertUser(user: UserEntity)

    suspend fun deleteUser(user: UserEntity)

    fun getAllUsers():Flow<List<UserEntity>>

    fun getUser(login: String):Flow<UserEntity?>

}