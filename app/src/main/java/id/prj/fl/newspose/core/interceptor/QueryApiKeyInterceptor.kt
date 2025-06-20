package id.prj.fl.newspose.core.interceptor

import id.prj.fl.newspose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class QueryApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        if (originalRequest.method != "GET") {
            return chain.proceed(originalRequest)
        }

        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.ARTICLE_API_KEY)
            .addQueryParameter("isDuplicateFilter", "skipDuplicates")
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}