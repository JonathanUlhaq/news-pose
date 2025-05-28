package id.prj.fl.newspose.core.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

fun <DataResponse : Any, DataModel : Any> fetchDataHandler(
    createCall: () -> Response<DataResponse>,
    responseToModel: (DataResponse) -> DataModel
): Flow<ResourceHandler<DataModel>> = flow {
    val apiCall = createCall();
    try {
        emit(ResourceHandler.Loading(true))
        if (apiCall.isSuccessful) {
            emit(ResourceHandler.Loading(false))
            if (apiCall.body() != null) {
                emit(ResourceHandler.Success(responseToModel(apiCall.body()!!)))
            } else {
                emit(ResourceHandler.Error(ErrorStatus.GeneralError("Response body kosong")))
            }
        } else {
            emit(ResourceHandler.Loading(false))
            val errorMessage = apiCall.errorBody()?.string() ?: "Terjadi kesalahan"
            emit(ResourceHandler.Error(ErrorStatus.GeneralError(errorMessage)))
        }
    } catch (e: HttpException) {
        emit(ResourceHandler.Loading(false))
        when (e.code()) {
            403 -> emit(ResourceHandler.Error(ErrorStatus.Forbidden))
            429 -> emit(ResourceHandler.Error(ErrorStatus.TooManyRequest))
            401 -> emit(ResourceHandler.Error(ErrorStatus.UserLimitAccess))
            else -> emit(ResourceHandler.Error(ErrorStatus.GeneralError(e.message())))
        }
    } catch (e: Exception) {
        emit(ResourceHandler.Loading(false))
        emit(ResourceHandler.Error(ErrorStatus.GeneralError(e.message ?: "Terjadi kesalahan")))

    }
}