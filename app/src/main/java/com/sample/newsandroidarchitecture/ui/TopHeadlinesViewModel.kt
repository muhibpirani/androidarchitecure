package com.sample.newsandroidarchitecture.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tubbr.data.repository.Resource
import com.sample.newsandroidarchitecture.data.repository.NewsRepository
import com.sample.newsandroidarchitecture.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class TopHeadlinesViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {

    init {
        getTopHeadlines()
        getEnterTainmentNews()
    }

    val articlesData = MutableLiveData<Resource<List<Article>>>()
    val entertainmentNews = MutableLiveData<Resource<List<Article>>>()

    fun getTopHeadlines() {
        viewModelScope.launch {
            newsRepository.getTopHeadLines().collect {
                articlesData.value = it
            }
        }
    }

    fun getEnterTainmentNews() {
        viewModelScope.launch {
            newsRepository.getEntertaimentNews().collect {
                entertainmentNews.value = it
            }
        }
    }
}