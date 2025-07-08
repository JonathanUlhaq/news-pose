package id.prj.fl.newspose.features.bookmarks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.bookmarks.data.dao.BookMarksDao
import id.prj.fl.newspose.features.bookmarks.data.repository.BookMarksRepositoryImpl
import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookMarksDomainModule {

    @Singleton
    @Provides
    fun provideBookMarksRepository(
        bookMarksDao: BookMarksDao
    ): MainBookMarkRepository =
        BookMarksRepositoryImpl(bookMarksDao)
}