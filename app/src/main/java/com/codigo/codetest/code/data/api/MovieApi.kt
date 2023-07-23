package com.codigo.codetest.code.data.api

import com.codigo.codetest.code.data.source.remote.list.MovieResponse
import com.codigo.codetest.code.data.util.ApiResponse
import com.codigo.codetest.code.data.util.EndPoints
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface MovieApi {
    @GET(EndPoints.POPULAR_MOVIES)
    fun getPopularMovies() : Flow<ApiResponse<MovieResponse>>

    @GET(EndPoints.UPCOMING_MOVIES)
    fun getUpcomingMovies() : Flow<ApiResponse<MovieResponse>>


}