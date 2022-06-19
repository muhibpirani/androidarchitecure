package com.sample.newsandroidarchitecture.ui.bottomnav

sealed class Screen(val route: String) {
    object ArticleDetails : Screen("details")
}