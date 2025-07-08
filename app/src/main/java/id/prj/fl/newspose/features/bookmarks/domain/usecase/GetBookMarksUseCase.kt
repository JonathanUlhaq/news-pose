package id.prj.fl.newspose.features.bookmarks.domain.usecase

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookMarksUseCase @Inject constructor(
    private val bookMarksRepository: MainBookMarkRepository
) {
    operator fun invoke(
        pageSize: Int,
        page: Int
    ): Flow<ResourceHandler<List<BookMarksModel>>> =
        bookMarksRepository.getBookMarks(pageSize, page)
}