package id.prj.fl.newspose.features.newsdetail.domain.model

import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel

data class NewsDetailModel(
    val info: NewsDetailInfoModel = NewsDetailInfoModel(),
)

data class NewsDetailInfoModel(
    val uri:String = "",
    val date: String = "",
    val title: String = "",
    val body: String = "",
    val image: String = "",
) {
    fun toBookMarksModel(): BookMarksModel =
        BookMarksModel(
            uri = this.uri,
            title = this.title,
            desc = this.body,
            date = this.date,
            image = this.image,
            dataType = "news"
        )
}