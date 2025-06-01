package id.prj.fl.newspose.features.newsdetail.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.newsdetail.data.service.NewsDetailService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsDetailDataModule {
    @Provides
    @Singleton
    fun newsDetailServiceProvider(retrofit: Retrofit): NewsDetailService =
        retrofit.create(NewsDetailService::class.java)
}