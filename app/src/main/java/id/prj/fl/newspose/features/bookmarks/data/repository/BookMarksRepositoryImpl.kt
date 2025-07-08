package id.prj.fl.newspose.features.bookmarks.data.repository

import id.prj.fl.newspose.core.database.fetchLocalDatabaseHandler
import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.data.dao.BookMarksDao
import id.prj.fl.newspose.features.bookmarks.data.entities.toListModel
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarksRepositoryImpl @Inject constructor(private val bookMarksDao: BookMarksDao) :
    MainBookMarkRepository {

    override fun getBookMarks(
        pageSize: Int,
        page: Int
    ): Flow<ResourceHandler<List<BookMarksModel>>> =
        fetchLocalDatabaseHandler(
            dataAccessObject = {
                bookMarksDao.getAllBookMarks(pageSize = pageSize, pageOffset = page)
            },
            dbModelToDomainModel = { entity ->
                entity.toListModel()
            }
        )

    override fun getBookMarkByUri(uri: String): Flow<ResourceHandler<BookMarksModel>> =
        fetchLocalDatabaseHandler(
            dataAccessObject = {
                bookMarksDao.getBookMarkByUri(uri)
            },
            dbModelToDomainModel = { entity ->
                entity.toModel()
            }
        )

    override fun isBookMarksExist(uri: String): Flow<ResourceHandler<Boolean>> =
        fetchLocalDatabaseHandler(
            dataAccessObject = {
                bookMarksDao.isBookMarked(uri)
            },
            dbModelToDomainModel = { isExist ->
                isExist
            }
        )

    override suspend fun addToBookMarks(bookMarksModel: BookMarksModel) {
        bookMarksDao.insertBookMark(bookMarksModel.toEntity())
    }

    override suspend fun deleteFromBookMarks(uri: String) {
        bookMarksDao.removeBookMark(uri)
    }
}