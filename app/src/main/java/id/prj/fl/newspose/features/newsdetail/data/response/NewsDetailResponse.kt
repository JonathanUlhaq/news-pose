package id.prj.fl.newspose.features.newsdetail.data.response

import id.prj.fl.newspose.features.newsdetail.domain.model.NewsDetailInfoModel
import id.prj.fl.newspose.features.newsdetail.domain.model.NewsDetailModel

data class NewsDetailResponse(
    val info: NewsDetailInfoResponse?,
)

data class NewsDetailInfoResponse(
    val uri: String?,
    val date: String?,
    val title: String?,
    val body: String?,
    val image: String?,
)

private fun NewsDetailInfoResponse?.toModel(): NewsDetailInfoModel =
    NewsDetailInfoModel(
        uri = this?.uri.orEmpty(),
        date = this?.date.orEmpty(),
        title = this?.title.orEmpty(),
        body = this?.body.orEmpty(),
        image = this?.image.orEmpty()
    )

fun NewsDetailResponse?.toModel(): NewsDetailModel =
    NewsDetailModel(this?.info.toModel())

typealias NewsDetailResponseMap = Map<String, NewsDetailResponse>