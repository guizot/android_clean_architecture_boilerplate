package com.guizot.android_clean_architecture_boilerplate.data.source.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
class GithubInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "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") // Replace with actual token
            .build()
        return chain.proceed(request)
    }
}