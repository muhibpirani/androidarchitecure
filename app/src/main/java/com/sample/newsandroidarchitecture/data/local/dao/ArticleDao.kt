package com.sample.newsandroidarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.newsandroidarchitecture.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(connections: List<Article>)

    @Query("DELETE FROM ${Article.TABLE_NAME}")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM ${Article.TABLE_NAME} ORDER BY publishedAt DESC")
    fun getAllArticles(): Flow<List<Article>>
}