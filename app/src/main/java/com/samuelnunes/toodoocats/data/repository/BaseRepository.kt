package com.samuelnunes.toodoocats.data.repository

import com.samuelnunes.toodoocats.domain.utils.Resource
import kotlinx.coroutines.flow.*
import retrofit2.Response

open class BaseRepository {


    inline fun <RemoteType> networkBoundResource(
        crossinline fetch: suspend () -> Response<RemoteType>,
        crossinline onFetchFailed: (Throwable) -> Unit = { }
    ): Flow<Resource<RemoteType>> = flow {
        emit(Resource.Loading())
        emit(
            try {
                val res = fetch()
                if (res.isSuccessful) {
                    Resource.Success(res.body()!!)
                } else {
                    Resource.Error(res.message())
                }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                Resource.Error(throwable)
            }
        )
    }

    inline fun <LocalType, RemoteType> networkBoundResource(
        crossinline query: () -> Flow<LocalType>,
        crossinline fetch: suspend () -> RemoteType,
        crossinline saveFetchResult: suspend (RemoteType) -> Unit,
        crossinline onFetchFailed: (Throwable) -> Unit = { },
        crossinline shouldFetch: (LocalType?) -> Boolean = { true }
    ) = flow {
        emit(Resource.Loading<RemoteType>())
        val data = query().firstOrNull()

        val flow = if (shouldFetch(data)) {
            emit(Resource.Loading<RemoteType>())

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


