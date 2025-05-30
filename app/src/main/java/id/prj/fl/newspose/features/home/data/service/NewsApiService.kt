package id.prj.fl.newspose.features.home.data.service

import id.prj.fl.newspose.BuildConfig
import id.prj.fl.newspose.features.home.data.response.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("article/getArticles")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = BuildConfig.ARTICLE_API_KEY,
        @Query("articlesPage") articlesPage:Int,
        @Query("articlesCount") articlesCount:Int,
        @Query("articlesSortBy") articleSortBy:String?,
        @Query("keyword") keyword:String?,
    ): Response<ArticlesResponse>
}