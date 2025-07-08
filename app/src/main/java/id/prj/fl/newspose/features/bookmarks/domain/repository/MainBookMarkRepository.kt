package id.prj.fl.newspose.features.bookmarks.domain.repository

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import kotlinx.coroutines.flow.Flow

interface MainBookMarkRepository {
    fun getBookMarks(pageSize: Int, page: Int): Flow<ResourceHandler<List<BookMarksModel>>>
    fun getBookMarkByUri(uri: String): Flow<ResourceHandler<BookMarksModel>>
    fun isBookMarksExist(uri: String): Flow<ResourceHandler<Boolean>>
    suspend fun addToBookMarks(bookMarksModel: BookMarksModel)
    suspend fun deleteFromBookMarks(uri: String)
}