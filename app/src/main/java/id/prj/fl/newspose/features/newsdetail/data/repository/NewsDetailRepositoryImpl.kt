package id.prj.fl.newspose.features.newsdetail.data.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.core.network.fetchDataHandler
import id.prj.fl.newspose.features.newsdetail.data.response.toModel
import id.prj.fl.newspose.features.newsdetail.data.service.NewsDetailService
import id.prj.fl.newspose.features.newsdetail.domain.model.NewsDetailModel
import id.prj.fl.newspose.features.newsdetail.domain.repository.NewsDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsDetailRepositoryImpl @Inject constructor(private val service: NewsDetailService) :
    NewsDetailRepository {
    override fun getDetailNews(articleUri: String): Flow<ResourceHandler<NewsDetailModel>> =
        fetchDataHandler(
            createCall = {
                service.getDetailNews(articleUri)
            },
            responseToModel = {response ->
                response.values.firstOrNull().toModel()
            }
        )
}