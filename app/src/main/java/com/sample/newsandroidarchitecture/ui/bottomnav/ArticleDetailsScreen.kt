package com.sample.newsandroidarchitecture.ui.bottomnav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sample.newsandroidarchitecture.model.Article

@Composable
fun ArticleDetailsScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(text = article.content ?: "")
    }
}