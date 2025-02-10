package com.guizot.android_clean_architecture_boilerplate.data.source.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubApiService {
    @GET("search/users")
    suspend fun searchUser(
        @QueryMap parameter: Map<String, Any>,
    ): Any

}