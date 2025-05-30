package id.prj.fl.newspose.features.home.data.response

import id.prj.fl.newspose.features.home.domain.model.ArticlesModel

data class ArticlesResponse(
    val articles: ArticleResultModel,
    val totalResults: Int?,
    val page: Int?,
    val count: Int?,
    val pages: Int?
) {
    data class ArticleResultModel(
        val results: List<ArticleListsResultResponse?>?
    ) {
        data class ArticleListsResultResponse(
            val uri: String?,
            val lang: String?,
            val date: String?,
            val time: String?,
            val dateTime: String?,
            val dateTimePub: String?,
            val url: String?,
            val title: String?,
            val body: String?,
            val image: String?,
        )
    }
}


fun List<ArticlesResponse.ArticleResultModel.ArticleListsResultResponse?>?.toListModel(): List<ArticlesModel.ArticleResultModel.ArticlesListResultModel> {
    return this?.map {
        ArticlesModel.ArticleResultModel.ArticlesListResultModel(
            uri = it?.uri.orEmpty(),
            lang = it?.lang.orEmpty(),
            date = it?.date.orEmpty(),
            time = it?.time.orEmpty(),
            dateTime = it?.dateTime.orEmpty(),
            dateTimePub = it?.dateTimePub.orEmpty(),
            url = it?.url.orEmpty(),
            title = it?.title.orEmpty(),
            body = it?.body.orEmpty(),
            image = it?.image.orEmpty(),
        )
    }.orEmpty()
}

fun ArticlesResponse.ArticleResultModel?.toModel(): ArticlesModel.ArticleResultModel =
    ArticlesModel.ArticleResultModel(this?.results.toListModel())

fun ArticlesResponse?.toModel(): ArticlesModel =
    ArticlesModel(
        articles = this?.articles.toModel(),
        totalResults = this?.totalResults ?: 0,
        page = this?.page ?: 0,
        count = this?.count ?: 0,
        pages = this?.pages ?: 0
    )