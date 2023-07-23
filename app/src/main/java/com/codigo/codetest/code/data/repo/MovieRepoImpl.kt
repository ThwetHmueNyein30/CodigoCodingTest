package com.codigo.codetest.code.data.repo

import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.api.MovieApi
import com.codigo.codetest.code.data.db.MovieDao
import com.codigo.codetest.code.data.source.local.MovieType
import com.codigo.codetest.code.data.source.mapper.toMovie
import com.codigo.codetest.code.data.source.mapper.toMovieEntity
import com.codigo.codetest.code.data.util.Result
import com.codigo.codetest.code.data.util.localRemoteBoundResult
import com.codigo.codetest.code.di.Dispatcher
import com.codigo.codetest.code.di.MovieDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieApi,
    private val dao: MovieDao,
    @Dispatcher(MovieDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : MovieRepo {
    override fun getPopularMovies(page: Int): Flow<Result<List<Movie>>> {
        return localRemoteBoundResult(
            fetchFromLocal = {
                dao.getAllMovies(page = page, type = MovieType.POPULAR.toString())
                    .map { movieEntities -> movieEntities.map { it.toMovie() } }
            },
            shouldMakeNetworkRequest = { dbData ->
                dbData.isNullOrEmpty()
            },
            makeNetworkRequest = {
                api.getPopularMovies(
                    page = page,
                )
            },
            saveResponseData = { movies ->
                dao.upsertMovie(movies.results.map { it.toMovieEntity(page = page,type= MovieType.POPULAR.name) })

            },
        ).flowOn(ioDispatcher)
    }

    override fun getUpcomingMovies(page: Int): Flow<Result<List<Movie>>> {
        return localRemoteBoundResult(
            fetchFromLocal = {
                dao.getAllMovies(page = page, type = MovieType.UPCOMING.toString())
                    .map { movieEntities -> movieEntities.map { it.toMovie() } }
            },
            shouldMakeNetworkRequest = { dbData ->
                dbData.isNullOrEmpty()
            },
            makeNetworkRequest = {
                api.getUpcomingMovies(
                    page = page,
                )
            },
            saveResponseData = { movies ->
                dao.upsertMovie(movies.results.map { it.toMovieEntity(page = page, type= MovieType.UPCOMING.toString()) })
            },
        ).flowOn(ioDispatcher)
    }

    override fun onSaveFavoriteMovie(id: Int, isFavorite: Boolean) {
        dao.setFavouriteMovie(id,isFavorite)
    }


}