package com.guizot.android_clean_architecture_boilerplate.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guizot.android_clean_architecture_boilerplate.data.data_source.remote.GithubApiService
import com.guizot.android_clean_architecture_boilerplate.data.mappers.toDomain
import com.guizot.android_clean_architecture_boilerplate.domain.model.User
import kotlinx.io.IOException
import retrofit2.HttpException

class GithubPagingSource(
    private val githubApiService: GithubApiService,
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val currentPage = params.key ?: 1
            val users = githubApiService.searchUser(
                mapOf(
                    "q" to "followers:>10000",
                    "per_page" to "10",
                    "page" to currentPage.toString()
                )
            )
            if (users.isSuccessful) {
                users.body()?.items?.let {
                    LoadResult.Page(
                        data = it.toDomain(),
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (it.isEmpty()) null else currentPage + 1
                    )
                } ?: run {
                    return LoadResult.Error(
                        Exception("error occurred: ${users.raw()}")
                    )
                }
            } else {
                return LoadResult.Error(
                    Exception("error occurred: ${users.raw()}")
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

}