package id.prj.fl.newspose.features.explore.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.features.explore.data.repository.ExploreNewsRepositoryImpl
import id.prj.fl.newspose.features.explore.data.service.ExploreNewsService
import id.prj.fl.newspose.features.explore.domain.repository.ExploreNewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExploreNewsDomainModule {

    @Provides
    @Singleton
    fun exploreNewsRepositoryProvider(service: ExploreNewsService): ExploreNewsRepository =
        ExploreNewsRepositoryImpl(service)
}