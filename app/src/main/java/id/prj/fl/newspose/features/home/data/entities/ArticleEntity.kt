package id.prj.fl.newspose.features.home.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.prj.fl.newspose.features.home.domain.model.ArticlesModel

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
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
    fun toArticleModel(): ArticlesModel.ArticleResultModel.ArticlesListResultModel =
        ArticlesModel.ArticleResultModel.ArticlesListResultModel(
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


