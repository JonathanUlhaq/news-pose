package id.prj.fl.newspose.features.newsdetail.data.service

import id.prj.fl.newspose.features.newsdetail.data.response.NewsDetailResponseMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDetailService {
    @GET("article/getArticle")
    suspend fun getDetailNews(
        @Query("articleUri") articleUri:String
    ):Response<NewsDetailResponseMap>
}