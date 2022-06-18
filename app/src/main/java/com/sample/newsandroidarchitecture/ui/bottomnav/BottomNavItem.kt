package com.sample.newsandroidarchitecture.ui.bottomnav

import com.sample.newsandroidarchitecture.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.ic_tech, "home")
    object Sports : BottomNavItem("Sports", R.drawable.ic_tech, "my_network")
    object Tech : BottomNavItem("Tech", R.drawable.ic_tech, "notification")
}