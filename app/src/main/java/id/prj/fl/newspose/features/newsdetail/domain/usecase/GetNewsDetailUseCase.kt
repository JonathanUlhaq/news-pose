package id.prj.fl.newspose.features.newsdetail.domain.usecase

import id.prj.fl.newspose.features.newsdetail.domain.repository.NewsDetailRepository
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(private val repository: NewsDetailRepository) {
    operator fun invoke(articleUri: String) = repository.getDetailNews(articleUri)
}