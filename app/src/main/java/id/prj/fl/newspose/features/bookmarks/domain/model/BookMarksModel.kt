package id.prj.fl.newspose.features.bookmarks.domain.model

import id.prj.fl.newspose.features.bookmarks.data.entities.BookMarksEntity

data class BookMarksModel(
    val uri: String = "",
    val title: String = "",
    val desc: String = "",
    val date: String = "",
    val image: String = "",
    val dataType: String = ""
) {
    fun toEntity() = BookMarksEntity(
        uri = this.uri,
        title = this.title,
        desc = this.desc,
        date = this.date,
        image = this.image,
        dataType = this.dataType
    )
}
