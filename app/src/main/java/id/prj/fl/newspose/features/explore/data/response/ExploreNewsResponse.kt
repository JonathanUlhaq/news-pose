package id.prj.fl.newspose.features.explore.data.response

import id.prj.fl.newspose.features.explore.domain.model.ExploreNewsModel

data class ExploreNewsResponse(
    val articles: ArticleResultResponse?,
    val totalResults: Int?,
    val page: Int?,
    val count: Int?,
    val pages: Int?
) {
    data class ArticleResultResponse(
        val results: List<ArticlesListResultResponse?>?
    ) {
        data class ArticlesListResultResponse(
            val uri: String?,
            val dataType: String?,
            val date: String?,
            val title: String?,
            val image: String?,
        )
    }
}

private fun List<ExploreNewsResponse.ArticleResultResponse.ArticlesListResultResponse?>?.toListModel():
        List<ExploreNewsModel.ArticleResultModel.ArticlesListResultModel> {
    return this?.map { response ->
        ExploreNewsModel.ArticleResultModel.ArticlesListResultModel(
            uri = response?.uri.orEmpty(),
            dataType = response?.dataType.orEmpty(),
            date = response?.date.orEmpty(),
            title = response?.title.orEmpty(),
            image = response?.image.orEmpty(),
        )
    } ?: emptyList()
}

private fun ExploreNewsResponse.ArticleResultResponse?.toModel(): ExploreNewsModel.ArticleResultModel =
    ExploreNewsModel.ArticleResultModel(
        results = this?.results.toListModel()
    )

fun ExploreNewsResponse.toModel(): ExploreNewsModel =
    ExploreNewsModel(
        articles = this.articles.toModel(),
        totalResults = this.totalResults ?: 0,
        page = this.page ?: 0,
        count = this.count ?: 0,
        pages = this.pages ?: 0,
    )
