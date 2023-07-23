package com.codigo.codetest.code.data.repo

import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.util.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getPopularMovies(page:Int): Flow<Result<List<Movie>>>

    fun getUpcomingMovies(page:Int): Flow<Result<List<Movie>>>

    fun onSaveFavoriteMovie(id: Int, isFavorite:Boolean)
}