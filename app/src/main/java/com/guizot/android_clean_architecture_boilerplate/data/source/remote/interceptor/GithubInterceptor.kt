package com.guizot.android_clean_architecture_boilerplate.data.source.remote.interceptor

import com.guizot.android_clean_architecture_boilerplate.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class GithubInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "")
            .build()
        return chain.proceed(request)
    }
}
