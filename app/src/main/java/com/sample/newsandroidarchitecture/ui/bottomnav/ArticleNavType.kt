package com.sample.newsandroidarchitecture.ui.bottomnav

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.sample.newsandroidarchitecture.model.Article

class ArticleNavType : NavType<Article>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Article? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Article {
        return try {
            Gson().fromJson(value, Article::class.java)
        } catch (exception: Exception) {
            return Article()
        }
    }

    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putParcelable(key, value)
    }
}