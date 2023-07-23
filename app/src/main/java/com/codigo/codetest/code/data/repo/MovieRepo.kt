package com.codigo.codetest.code.data.repo

import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.util.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getPopularMovies(): Flow<Result<List<Movie>>>

    fun getUpcomingMovies(): Flow<Result<List<Movie>>>

    suspend fun onSaveFavoriteMovie(id: Int, isFavorite:Boolean)
}