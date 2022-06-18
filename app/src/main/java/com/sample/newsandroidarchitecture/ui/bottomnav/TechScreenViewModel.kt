package com.sample.newsandroidarchitecture.ui.bottomnav

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.newsandroidarchitecture.data.repository.NewsRepository
import com.sample.newsandroidarchitecture.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TechScreenViewModel @Inject constructor(private val newsRepository: NewsRepository) :
    ViewModel() {
    init {
        getTopHeadlines()
    }

    val articlesData = MutableLiveData<List<Article>>()
    val mutableArticleData =  mutableStateListOf<Article>()
    fun getTopHeadlines() {
        viewModelScope.launch {
            newsRepository.getTopHeadLines().collect {
                mutableArticleData.addAll(it.data ?: emptyList())
                articlesData.value = it.data ?: emptyList()
            }
        }
    }
}