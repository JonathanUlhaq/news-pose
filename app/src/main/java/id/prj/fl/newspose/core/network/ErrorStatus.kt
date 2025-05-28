package id.prj.fl.newspose.core.network

sealed class ErrorStatus {
    data object UserLimitAccess : ErrorStatus()
    data object TooManyRequest : ErrorStatus()
    data object Forbidden : ErrorStatus()
    data class GeneralError(val message:String) : ErrorStatus()
}