package id.prj.fl.newspose.features.newsdetail.domain.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.newsdetail.domain.model.NewsDetailModel
import kotlinx.coroutines.flow.Flow

interface NewsDetailRepository {
    fun getDetailNews(articleUri: String): Flow<ResourceHandler<NewsDetailModel>>
}