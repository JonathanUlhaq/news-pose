package id.prj.fl.newspose.features.explore.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.explore.data.service.ExploreNewsService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExploreNewsDataModule {

    @Provides
    @Singleton
    fun exploreNewsServiceProvider(retrofit: Retrofit): ExploreNewsService =
        retrofit.create(ExploreNewsService::class.java)
}