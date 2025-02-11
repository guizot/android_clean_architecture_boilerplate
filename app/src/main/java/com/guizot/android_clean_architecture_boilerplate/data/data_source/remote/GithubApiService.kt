package com.guizot.android_clean_architecture_boilerplate.data.data_source.remote

import com.guizot.android_clean_architecture_boilerplate.data.model.UserDto
import com.guizot.android_clean_architecture_boilerplate.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubApiService {
    @GET("search/users")
    suspend fun searchUser(
        @QueryMap parameter: Map<String, String>,
    ): Response<UserResponse<UserDto>>

}