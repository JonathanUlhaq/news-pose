package id.prj.fl.newspose.features.home.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.home.data.service.NewsApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeDataModule {

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)
}