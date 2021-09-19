package com.sample.newsandroidarchitecture.data.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthInterceptor constructor(
    private val context: Context
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        // val token = pref.getString(Constants.AUTH_TOKEN)

        // To add generic query params
        val httpUrl = original.url.newBuilder()
            .addQueryParameter("apiKey", "ad2b90c4e9db47c5a5671d8c222f2c9a")
            .build()
        var requestBuilder: Request.Builder? = null

        // To add header


        /* if (!token.isBlank()) {
             requestBuilder = original.newBuilder()
                 .addHeader("Authorization", "token " + token)
                 .url(httpUrl)
         }*/
        requestBuilder = original.newBuilder()
            .url(httpUrl)

        val response = chain.proceed(requestBuilder.build())
        if (response.code == 401) {
            // handle user unauthorized state
        }
        return response
    }
}