package com.guizot.android_clean_architecture_boilerplate.core.di

import com.guizot.android_clean_architecture_boilerplate.data.source.remote.GithubApiService
import com.guizot.android_clean_architecture_boilerplate.data.source.remote.interceptor.GithubInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(): GithubInterceptor {
        return GithubInterceptor()
    }

    @Provides
    @Singleton
    fun provideGithubApiService(
        githubInterceptor: GithubInterceptor
    ): GithubApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(githubInterceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)
    }

}