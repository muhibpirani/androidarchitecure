package app.tubbr.data.local

import android.content.Context
import androidx.room.*
import com.sample.newsandroidarchitecture.data.local.converter.SourceConverter
import com.sample.newsandroidarchitecture.data.local.dao.ArticleDao
import com.sample.newsandroidarchitecture.model.Article

@Database(
    entities = [Article::class],
    version = DatabaseMigrations.DB_VERSION
)
@TypeConverters(
    SourceConverter::class
)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * @return [DAO] Posts Data Access Object.
     */
    abstract fun getArticleDao(): ArticleDao

    companion object {
        const val DB_NAME = "sample_database"

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}