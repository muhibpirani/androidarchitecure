package com.sample.newsandroidarchitecture.data.dto

import com.google.gson.annotations.SerializedName
import com.sample.newsandroidarchitecture.model.Article


data class ArticlesResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("totalResults")
    var totalResults: Int? = null,

    @SerializedName("articles")
    var articles: List<Article>? = null
)