package id.prj.fl.newspose.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

fun <DataResponse : Any, DataModel : Any> fetchDataHandler(
    createCall: suspend () -> Response<DataResponse>,
    responseToModel: (DataResponse) -> DataModel
): Flow<ResourceHandler<DataModel>> = flow {
    try {
        val apiCall = createCall()
        if (apiCall.isSuccessful) {
            if (apiCall.body() != null) {
                emit(ResourceHandler.Success(responseToModel(apiCall.body()!!)))
            } else {
                emit(ResourceHandler.Error(ErrorStatus.GeneralError("Response empty boy")))
            }
        } else {
            val errorMessage = apiCall.errorBody()?.string() ?: "System Error"
            emit(ResourceHandler.Error(ErrorStatus.GeneralError(errorMessage)))
        }
    } catch (e: HttpException) {
        when (e.code()) {
            403 -> emit(ResourceHandler.Error(ErrorStatus.Forbidden))
            429 -> emit(ResourceHandler.Error(ErrorStatus.TooManyRequest))
            401 -> emit(ResourceHandler.Error(ErrorStatus.UserLimitAccess))
            else -> emit(ResourceHandler.Error(ErrorStatus.GeneralError(e.message())))
        }
    } catch (e: Exception) {
        emit(ResourceHandler.Error(ErrorStatus.GeneralError(e.message ?: "System Error")))
    }
}