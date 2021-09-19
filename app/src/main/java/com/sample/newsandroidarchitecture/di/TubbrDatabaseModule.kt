package app.tubbr.di

import android.app.Application
import app.tubbr.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object TubbrDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = NewsDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providesArticleDao(database: NewsDatabase) = database.getArticleDao()

}