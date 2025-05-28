package id.prj.fl.newspose.core.network

sealed class ResourceHandler<out T> {
    data class Success<T>(val data: T) : ResourceHandler<T>()
    data class Error(val errorStatus: ErrorStatus?) : ResourceHandler<Nothing>()
    data class Loading(val isLoading: Boolean) : ResourceHandler<Nothing>()
}