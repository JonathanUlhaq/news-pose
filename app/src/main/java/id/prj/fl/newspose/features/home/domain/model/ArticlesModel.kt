package id.prj.fl.newspose.features.home.domain.model

import java.util.UUID

data class ArticlesModel(
    val articles: ArticleResultModel,
    val totalResults: Int,
    val page: Int,
    val count: Int,
    val pages: Int
) {
    data class ArticleResultModel(
        val results: List<ArticlesListResultModel>
    ) {
        data class ArticlesListResultModel(
            val id:UUID = UUID.randomUUID(),
            val uri: String,
            val lang: String,
            val date: String,
            val time: String,
            val dateTime: String,
            val dateTimePub: String,
            val url: String,
            val title: String,
            val body: String,
            val image: String,
        )
    }

}