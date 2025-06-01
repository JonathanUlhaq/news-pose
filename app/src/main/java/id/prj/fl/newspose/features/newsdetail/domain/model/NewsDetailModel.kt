package id.prj.fl.newspose.features.newsdetail.domain.model

data class NewsDetailModel(
    val info: NewsDetailInfoModel = NewsDetailInfoModel(),
)

data class NewsDetailInfoModel(
    val date: String = "",
    val title: String = "",
    val body: String = "",
    val image: String = "",
)