package id.prj.fl.newspose.features.newsdetail.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.newsdetail.data.repository.NewsDetailRepositoryImpl
import id.prj.fl.newspose.features.newsdetail.data.service.NewsDetailService
import id.prj.fl.newspose.features.newsdetail.domain.repository.NewsDetailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsDetailDomainModule {

    @Provides
    @Singleton
    fun newsDetailRepoistoryProvider(service: NewsDetailService): NewsDetailRepository =
        NewsDetailRepositoryImpl(service)
}