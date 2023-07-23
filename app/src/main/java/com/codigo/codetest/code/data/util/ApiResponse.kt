package com.codigo.codetest.code.data.util

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiErrorResponse("Empty Data", response.code())
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if (message.isNullOrEmpty()) response.message() else message
                ApiErrorResponse(
                    errorMessage = errorMessage,
                    httpStatusCode = response.code()
                )
            }
        }

    }
}


data class ApiSuccessResponse<T>(
    val body: T,
) : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val errorMessage: String,
    val httpStatusCode: Int,
) : ApiResponse<T>()

class ThrowableError(message: String) : Throwable(message)
