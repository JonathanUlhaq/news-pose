package id.prj.fl.newspose.features.home.domain.model

import id.prj.fl.newspose.features.home.data.entities.ArticleEntity


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
        ) {
            fun toEntity(): ArticleEntity =
                ArticleEntity(
                    uri = this.uri,
                    lang = this.lang,
                    date = this.date,
                    time = this.time,
                    dateTime = this.dateTime,
                    dateTimePub = this.dateTimePub,
                    url = this.url,
                    title = this.title,
                    body = this.body,
                    image = this.image
                )
        }
    }

}