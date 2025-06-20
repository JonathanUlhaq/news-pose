package id.prj.fl.newspose.features.explore.domain.usecase

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.explore.domain.model.ExploreNewsModel
import id.prj.fl.newspose.features.explore.domain.repository.ExploreNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsArticlesUseCase @Inject constructor(
    private val repository: ExploreNewsRepository
) {
    operator fun invoke(
        sortBy: String?,
        keyword: String,
        articleCounts: Int,
        page: Int
    ): Flow<ResourceHandler<ExploreNewsModel>> =
        repository.searchNewsArticles(
            sortBy = sortBy,
            keyword = keyword,
            articleCounts = articleCounts,
            page = page
        )
}