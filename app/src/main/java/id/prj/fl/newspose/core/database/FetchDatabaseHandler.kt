package id.prj.fl.newspose.core.database

import id.prj.fl.newspose.core.network.ErrorStatus
import id.prj.fl.newspose.core.network.ResourceHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <DbEntity : Any, DomainModel : Any> fetchLocalDatabaseHandler(
    dataAccessObject: suspend () -> DbEntity,
    dbModelToDomainModel: (DbEntity) -> DomainModel
):Flow<ResourceHandler<DomainModel>> = flow<ResourceHandler<DomainModel>> {
    val result = dataAccessObject()
    emit(ResourceHandler.Success(dbModelToDomainModel(result)))
}.catch { exception ->
    emit(ResourceHandler.Error(ErrorStatus.GeneralError(exception.message ?: "System Error")))
}