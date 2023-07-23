package com.codigo.codetest.code.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codigo.codetest.code.data.repo.MovieRepo
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.util.Result
import kotlinx.coroutines.flow.single

class MovieListPagingDataSource(
    private val repo: MovieRepo,
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = params.key?.takeIf { it > 0 } ?: 1
        return try {
            when (val response = repo.getPopularMovies(currentPage).single()) {
                is Result.Success -> {
                    with(response) {
                        if (data.isNotEmpty()) {
                            LoadResult.Page(
                                data = data,
                                prevKey = if (currentPage > 1) currentPage - 1 else null,
                                nextKey = if (data.isNotEmpty()) currentPage + 1 else null
                            )
                        } else {
                            LoadResult.Page(
                                data = emptyList(),
                                prevKey = null,
                                nextKey = null
                            )
                        }
                    }

                }

                is Result.Loading -> {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = null,
                        nextKey = null
                    )
                }

                is Result.Error -> {
                    LoadResult.Error(Throwable(response.exception))
                }

            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
