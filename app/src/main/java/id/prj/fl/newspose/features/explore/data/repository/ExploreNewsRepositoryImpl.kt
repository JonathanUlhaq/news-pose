package id.prj.fl.newspose.features.explore.data.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.core.network.fetchDataHandler
import id.prj.fl.newspose.features.explore.data.response.ExploreNewsResponse
import id.prj.fl.newspose.features.explore.data.response.toModel
import id.prj.fl.newspose.features.explore.data.service.ExploreNewsService
import id.prj.fl.newspose.features.explore.domain.model.ExploreNewsModel
import id.prj.fl.newspose.features.explore.domain.repository.ExploreNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExploreNewsRepositoryImpl @Inject constructor(
    private val service: ExploreNewsService
) : ExploreNewsRepository {
    override fun searchNewsArticles(
        sortBy: String?,
        keyword: String,
        articleCounts: Int,
        page: Int
    ): Flow<ResourceHandler<ExploreNewsModel>> =
        fetchDataHandler(
            createCall = {
                service.getArticles(
                    articlesPage = page,
                    articlesCount = articleCounts,
                    articleSortBy = sortBy,
                    keyword = keyword
                )
            },
            responseToModel = {
                it.toModel()
            }
        )
}