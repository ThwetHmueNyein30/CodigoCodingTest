package com.codigo.codetest.code.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <D, R> localRemoteBoundResult(
    crossinline fetchFromLocal: () -> Flow<D>,
    crossinline shouldMakeNetworkRequest: (D?) -> Boolean = { true },
    crossinline makeNetworkRequest: () -> Flow<ApiResponse<R>>,
    crossinline saveResponseData: suspend (R) -> Unit = {},
): Flow<Result<D>> = flow {
    emit(Result.Loading)
    val localData = fetchFromLocal().first()
    if (shouldMakeNetworkRequest(localData)) {
        makeNetworkRequest()
            .flatMapConcat { apiResponse ->
                when (apiResponse) {
                    is ApiSuccessResponse -> {
                        apiResponse.body?.let { saveResponseData(it) }

                        fetchFromLocal().map {
                            Result.Success(it)
                        }
                    }

                    is ApiErrorResponse -> {
                        fetchFromLocal().map { Result.Error(apiResponse.errorMessage) }
                    }
                }
            }
            .collect { result -> emit(result) }
    } else {
        fetchFromLocal()
            .map { Result.Success(it) }
            .collect { result -> emit(result) }
    }
}


