package id.prj.fl.newspose.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.prj.fl.newspose.core.datastore.NewsDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun newsDataStoreProvider(@ApplicationContext context: Context): NewsDataStore =
        NewsDataStore(context)
}