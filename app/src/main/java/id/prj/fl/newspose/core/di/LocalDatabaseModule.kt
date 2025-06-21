package id.prj.fl.newspose.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.core.database.NewsAppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsAppDatabase =
        Room.databaseBuilder(
            context,
            NewsAppDatabase::class.java,
            "news_article_database"
        ).build()

    @Provides
    fun provideHomeArticleDao(database: NewsAppDatabase) =
        database.homeArticleDao()
}