package com.sample.newsandroidarchitecture.ui.bottomnav

import HomeScreen
import SportsScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sample.newsandroidarchitecture.model.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Sports.screen_route) {
            SportsScreen()
        }
        composable(BottomNavItem.Tech.screen_route) {
            TechScreen(hiltViewModel(), navController)
        }
        composable(
            Screen.ArticleDetails.route+"/{article}",
            arguments = listOf(navArgument("article") { type = ArticleNavType() })
        )
        {
            val article: Article? = it.arguments?.getParcelable("article")
            ArticleDetailsScreen(article = (article ?: Article()))
        }
    }
}