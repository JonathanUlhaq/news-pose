package id.prj.fl.newspose.features.bookmarks.domain.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import kotlinx.coroutines.flow.Flow

interface MainBookMarkRepository {
    fun getBookMarks(): Flow<ResourceHandler<List<BookMarksModel>>>
    fun isBookMarksExist(uri: String): Flow<ResourceHandler<Boolean>>
    suspend fun addToBookMarks(
        uri: String,
        title: String,
        date: String,
        image: String,
        dataType: String
    )

    suspend fun deleteFromBookMarks(uri: String)
}