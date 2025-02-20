package com.guizot.android_clean_architecture_boilerplate.core.di

import android.content.Context
import com.guizot.android_clean_architecture_boilerplate.core.data.AppDatabase
import com.guizot.android_clean_architecture_boilerplate.data.data_source.local.GithubUserDao
import com.guizot.android_clean_architecture_boilerplate.data.data_source.remote.GithubApiService
import com.guizot.android_clean_architecture_boilerplate.data.data_source.remote.interceptor.GithubInterceptor
import com.guizot.android_clean_architecture_boilerplate.data.repositories.GithubRepositoryImpl
import com.guizot.android_clean_architecture_boilerplate.domain.repositories.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideGithubInterceptor(): GithubInterceptor {
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

    @Provides
    fun provideGithubRepository(
        githubApiService: GithubApiService,
        githubUserDao: GithubUserDao
    ): GithubRepository {
        return GithubRepositoryImpl(githubApiService, githubUserDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

    @Provides
    fun provideGithubUserDao(appDatabase: AppDatabase): GithubUserDao {
        return appDatabase.getGithubUserDao()
    }

}