package id.prj.fl.newspose.features.explore.domain.model

data class ExploreNewsModel(
    val articles: ArticleResultModel = ArticleResultModel(),
    val totalResults: Int = 0,
    val page: Int = 0,
    val count: Int = 0,
    val pages: Int = 0,
) {
    data class ArticleResultModel(
        val results: List<ArticlesListResultModel> = emptyList()
    ) {
        data class ArticlesListResultModel(
            val uri: String = "",
            val dataType: String = "",
            val date: String = "",
            val title: String = "",
            val image: String = "",
        )
    }
}

