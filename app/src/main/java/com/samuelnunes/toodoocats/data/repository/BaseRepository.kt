package com.samuelnunes.toodoocats.data.repository

import com.samuelnunes.toodoocats.domain.utils.Resource
import kotlinx.coroutines.flow.*

open class BaseRepository {

    inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit,
        crossinline onFetchFailed: (Throwable) -> Unit = { },
        crossinline shouldFetch: (ResultType?) -> Boolean = { true }
    ) = flow {
        emit(Resource.Loading<RequestType>())
        val data = query().firstOrNull()

        val flow = if (shouldFetch(data)) {
            emit(Resource.Loading<RequestType>())

            try {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map { Resource.Error(throwable, it) }
            }
        } else {
            query().map { Resource.Success(it) }
        }
        emitAll(flow)
    }
}


