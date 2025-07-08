package id.prj.fl.newspose.features.newsdetail.domain.usecase

import id.prj.fl.newspose.features.bookmarks.domain.model.BookMarksModel
import id.prj.fl.newspose.features.bookmarks.domain.repository.MainBookMarkRepository
import javax.inject.Inject

class SaveBookMarksUseCase @Inject constructor(
    private val bookMarkRepository: MainBookMarkRepository
) {
    suspend operator fun invoke(bookMarksModel: BookMarksModel) {
        bookMarkRepository.addToBookMarks(bookMarksModel)
    }
}