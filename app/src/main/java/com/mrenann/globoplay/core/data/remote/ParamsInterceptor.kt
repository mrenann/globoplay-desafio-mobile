package com.mrenann.globoplay.core.data.remote

import com.mrenann.globoplay.BuildConfig
import com.mrenann.globoplay.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val apiKey = BuildConfig.API_KEY
        val url = request.url.newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .addQueryParameter(Constants.API_KEY_PARAM, apiKey)
            .build()

        val builderRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(builderRequest)
    }

}