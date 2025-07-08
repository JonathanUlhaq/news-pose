package id.prj.fl.newspose.features.bookmarks.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.core.database.NewsAppDatabase
import id.prj.fl.newspose.features.bookmarks.data.dao.BookMarksDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookMarksDataModule {

    @Provides
    @Singleton
    fun provideBookMarksDao(database: NewsAppDatabase): BookMarksDao =
        database.bookMarksDao()
}