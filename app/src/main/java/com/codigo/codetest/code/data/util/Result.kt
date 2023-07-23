
package com.codigo.codetest.code.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: String? = null) : Result<Nothing>
    object Loading : Result<Nothing>
}



fun <T> Flow<ApiResponse<T>>.asFlowResult(): Flow<Result<T>> {
    return this.map { response ->
        when (response) {
            is ApiSuccessResponse -> Result.Success(response.body)
            is ApiErrorResponse -> Result.Error(response.errorMessage)
        }
    }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it.message)) }
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return map { Result.Success(it) }
        .onStart { Result.Loading }
        .catch { Result.Error(it.message) }
}