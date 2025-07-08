package id.prj.fl.newspose.features.newsdetail.domain.usecase

import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import javax.inject.Inject

class CheckSavedBookMarksUseCase @Inject constructor(
    private val bookMarksRepository: MainBookMarkRepository
) {
    operator fun invoke(uri: String) =
        bookMarksRepository.isBookMarksExist(uri)
}