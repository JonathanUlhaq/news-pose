package id.prj.fl.newspose.features.bookmarks.domain.usecase

import id.prj.fl.newspose.core.network.ResourceHandler
import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookMarkByUriUseCase @Inject constructor(
    private val mainBookMarkRepository: MainBookMarkRepository
) {
    operator fun invoke(uri: String): Flow<ResourceHandler<BookMarksModel>> =
        mainBookMarkRepository.getBookMarkByUri(uri)
}