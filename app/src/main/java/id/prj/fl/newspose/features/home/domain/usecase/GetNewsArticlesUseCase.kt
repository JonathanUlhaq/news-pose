package id.prj.fl.newspose.features.home.domain.usecase

import id.prj.fl.newspose.features.home.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository)  {
    operator fun invoke(sortBy:String?,keyword:List<String>,articleCounts:Int,page:Int) = newsRepository.getNewsArticle(sortBy,keyword,articleCounts,page)
}