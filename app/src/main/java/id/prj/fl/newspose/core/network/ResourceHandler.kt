package id.prj.fl.newspose.core.network

sealed class ResourceHandler<out Data> {
    data class Success<out Data>(val data: Data) : ResourceHandler<Data>()
    data class Error(val errorStatus: ErrorStatus) : ResourceHandler<Nothing>()
}