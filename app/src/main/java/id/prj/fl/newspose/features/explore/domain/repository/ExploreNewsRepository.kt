package id.prj.fl.newspose.features.explore.domain.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.explore.domain.model.ExploreNewsModel
import kotlinx.coroutines.flow.Flow

interface ExploreNewsRepository {
    fun searchNewsArticles(
        sortBy: String?,
        keyword: String,
        articleCounts: Int,
        page: Int
    ): Flow<ResourceHandler<ExploreNewsModel>>
}