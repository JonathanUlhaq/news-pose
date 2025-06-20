package id.prj.fl.newspose.features.explore.data.service

import id.prj.fl.newspose.features.explore.data.response.ExploreNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreNewsService {
    @GET("article/getArticles")
    suspend fun getArticles(
        @Query("articlesPage") articlesPage: Int,
        @Query("articlesCount") articlesCount: Int,
        @Query("articlesSortBy") articleSortBy: String?,
        @Query("keyword") keyword: String,
    ): Response<ExploreNewsResponse>
}