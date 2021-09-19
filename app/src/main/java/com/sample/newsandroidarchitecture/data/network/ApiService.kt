package com.sample.newsandroidarchitecture.data.network

import com.sample.newsandroidarchitecture.data.dto.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=us")
    suspend fun getTopHeadlines(): Response<ArticlesResponse>

    @GET("category=entertainment")
    suspend fun getEntertainmentNews(): Response<ArticlesResponse>
}