package com.codigo.codetest.code.data.api

import com.codigo.codetest.code.data.source.remote.list.MovieResponse
import com.codigo.codetest.code.data.util.ApiResponse
import com.codigo.codetest.code.data.util.Constant.QUERY_PAGE
import com.codigo.codetest.code.data.util.EndPoints
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(EndPoints.POPULAR_MOVIES)
    fun getPopularMovies( @Query(QUERY_PAGE) page: Int) : Flow<ApiResponse<MovieResponse>>

    @GET(EndPoints.UPCOMING_MOVIES)
    fun getUpcomingMovies(@Query(QUERY_PAGE) page: Int) : Flow<ApiResponse<MovieResponse>>


}