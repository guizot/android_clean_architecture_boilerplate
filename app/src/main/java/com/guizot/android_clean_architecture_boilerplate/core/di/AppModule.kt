package com.guizot.android_clean_architecture_boilerplate.core.di

import android.content.Context
import com.guizot.android_clean_architecture_boilerplate.core.data.AppDatabase
import com.guizot.android_clean_architecture_boilerplate.core.data.PrettyLogger
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
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(
            logger = PrettyLogger(tag = "HTTP")
        ).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideGithubApiService(
        githubInterceptor: GithubInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): GithubApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(githubInterceptor)
                    // .addInterceptor(loggingInterceptor)
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