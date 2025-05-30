package id.prj.fl.newspose.features.home.data.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.core.network.fetchDataHandler
import id.prj.fl.newspose.features.home.data.response.toModel
import id.prj.fl.newspose.features.home.data.service.NewsApiService
import id.prj.fl.newspose.features.home.domain.model.ArticlesModel
import id.prj.fl.newspose.features.home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val serivce: NewsApiService) : NewsRepository {
    override fun getNewsArticle(
        sortBy: String?,
        keyword: String?,
        articleCounts:Int,
        page:Int,
    ): Flow<ResourceHandler<ArticlesModel>> = fetchDataHandler(
        createCall = {
            serivce.getArticles(
            articlesCount = articleCounts,
            articlesPage = page,
            articleSortBy = sortBy,
            keyword = keyword
        )
        },
        responseToModel = {
            it.toModel()
        }
    )

}