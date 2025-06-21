package id.prj.fl.newspose.features.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.home.data.dao.HomeArticleDao
import id.prj.fl.newspose.features.home.data.repository.NewsRepositoryImpl
import id.prj.fl.newspose.features.home.data.service.NewsApiService
import id.prj.fl.newspose.features.home.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeDomainModule {

    @Provides
    @Singleton
    fun newsRepositoryProvider(
        service: NewsApiService,
        homeArticleDao: HomeArticleDao
    ): NewsRepository =
        NewsRepositoryImpl(service, homeArticleDao)
}