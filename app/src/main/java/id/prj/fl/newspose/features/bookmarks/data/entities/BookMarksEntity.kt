package id.prj.fl.newspose.features.bookmarks.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel

@Entity(tableName = "bookmark_table")
data class BookMarksEntity(
    @PrimaryKey
    val uri: String,
    val title: String,
    val date: String,
    val image: String,
    val dataType: String
)

fun BookMarksEntity.toModel() = BookMarksModel(
    uri = this.uri,
    title = this.title,
    date = this.date,
    image = this.image,
    dataType = this.dataType
)
