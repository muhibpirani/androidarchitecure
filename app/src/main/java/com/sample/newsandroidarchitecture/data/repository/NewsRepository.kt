package com.sample.newsandroidarchitecture.data.repository

import app.tubbr.data.repository.NetworkBoundRepository
import app.tubbr.data.repository.Resource
import com.sample.newsandroidarchitecture.data.dto.ArticlesResponse
import com.sample.newsandroidarchitecture.data.local.dao.ArticleDao
import com.sample.newsandroidarchitecture.data.network.ApiService
import com.sample.newsandroidarchitecture.model.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val apiService: ApiService
) {

    @ExperimentalCoroutinesApi
    suspend fun getTopHeadLines(): Flow<Resource<List<Article>>> {
        return object : NetworkBoundRepository<List<Article>, ArticlesResponse>() {
            override suspend fun saveRemoteData(response: ArticlesResponse) {
                if (!response.articles.isNullOrEmpty()) {
                    articleDao.deleteAllArticles() // remove all locally stored as received from network
                    articleDao.insertArticles(response.articles!!) // insert articles
                }
            }

            override fun fetchFromLocal(): Flow<List<Article>> {
                return articleDao.getAllArticles()
            }

            override suspend fun fetchFromRemote(): Response<ArticlesResponse> {
                return apiService.getTopHeadlines()
            }

        }.asFlow()
    }

    @ExperimentalCoroutinesApi
    suspend fun getEntertaimentNews(): Flow<Resource<List<Article>>> {
        return object : NetworkBoundRepository<List<Article>, ArticlesResponse>() {
            override suspend fun saveRemoteData(response: ArticlesResponse) {
                if (!response.articles.isNullOrEmpty()) {
                    articleDao.deleteAllArticles() // remove all locally stored as received from network
                    articleDao.insertArticles(response.articles!!) // insert articles
                }
            }

            override fun fetchFromLocal(): Flow<List<Article>> {
                return articleDao.getAllArticles()
            }

            override suspend fun fetchFromRemote(): Response<ArticlesResponse> {
                return apiService.getEntertainmentNews()
            }

        }.asFlow()
    }
}