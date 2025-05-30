package id.prj.fl.newspose.features.home.domain.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.home.domain.model.ArticlesModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsArticle(sortBy: String?, keyword: String?,articleCounts:Int,page:Int):Flow<ResourceHandler<ArticlesModel>>
}