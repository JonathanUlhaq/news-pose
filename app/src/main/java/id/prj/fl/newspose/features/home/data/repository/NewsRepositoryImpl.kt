package id.prj.fl.newspose.features.home.data.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.core.network.fetchDataHandler
import id.prj.fl.newspose.features.home.data.dao.HomeArticleDao
import id.prj.fl.newspose.features.home.data.response.toModel
import id.prj.fl.newspose.features.home.data.service.NewsApiService
import id.prj.fl.newspose.features.home.domain.model.ArticlesModel
import id.prj.fl.newspose.features.home.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val serivce: NewsApiService,
    val homeArticleDao: HomeArticleDao
) : NewsRepository {
    override fun getNewsArticle(
        sortBy: String?,
        keyword: List<String>,
        articleCounts: Int,
        page: Int,
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
        },
        isOfflineFirstEnabled = { true },
        saveToLocal = { articlesModel ->
            if (page == 1) {
                homeArticleDao.clearArticles()
                homeArticleDao.insertArticles(articlesModel.articles.results.map { it.toEntity() })
            }
        },
        fetchFromLocal = {
            if (page == 1) {
                val localArticles = homeArticleDao.getArticles()
                if (localArticles.isNotEmpty()) {
                    val articleResult = ArticlesModel.ArticleResultModel(
                        results = localArticles.map { it.toArticleModel() }
                    )
                    ArticlesModel(
                        pages = localArticles.size,
                        page = page,
                        articles = articleResult,
                        totalResults = localArticles.size,
                        count = 5
                    )
                } else {
                    null
                }
            } else null
        }
    )

}