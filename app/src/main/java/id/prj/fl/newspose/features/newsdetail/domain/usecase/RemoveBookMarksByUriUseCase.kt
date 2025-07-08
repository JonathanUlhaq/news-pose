package id.prj.fl.newspose.features.newsdetail.domain.usecase

import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import javax.inject.Inject

class RemoveBookMarksByUriUseCase @Inject constructor(
    private val bookMarksRepository: MainBookMarkRepository
) {
    suspend operator fun invoke(uri: String) {
        bookMarksRepository.deleteFromBookMarks(uri)
    }
}