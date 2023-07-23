package com.codigo.codetest.code.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.repo.MovieRepo
import com.codigo.codetest.code.data.source.MovieListPagingDataSource
import com.codigo.codetest.code.data.util.Constant.DEFAULT_PAGE_SIZE
import com.codigo.codetest.code.data.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieRepo,
) : ViewModel() {
    var popularMoviePage = MutableStateFlow(1)
    var upComingMoviePage = MutableStateFlow(1)

    val popularMovieUiState: StateFlow<MovieUiState> = getPopularMovies(popularMoviePage.value).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MovieUiState.Loading,
    )

    val upComingMovieUiState: StateFlow<MovieUiState> = getUpcomingMovies(upComingMoviePage.value).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MovieUiState.Loading,
    )



    private fun getPopularMovies(page: Int): Flow<MovieUiState> {

        return repo.getPopularMovies(page).map { response ->
            when (response) {
                is Result.Success -> {
                    val allMovies = response.data
                    MovieUiState.Success(allMovies)
                }

                is Result.Loading -> {
                    MovieUiState.Loading
                }

                is Result.Error -> {
                    MovieUiState.Error(response.exception ?: "Something went wrong")
                }
            }
        }

    }

    private fun getUpcomingMovies(page: Int): Flow<MovieUiState> {
        return repo.getUpcomingMovies(page).map { response ->
            when (response) {
                is Result.Success -> {
                    val allMovies = response.data
                    MovieUiState.Success(allMovies)
                }

                is Result.Loading -> {
                    MovieUiState.Loading
                }

                is Result.Error -> {
                    MovieUiState.Error(response.exception ?: "Something went wrong")
                }
            }
        }

    }

    fun onFavoriteMovieTap(){
        repo.onSaveFavoriteMovie(254128,true)
    }



}


sealed interface MovieUiState {
    data class Success(val data: List<Movie>) : MovieUiState
    data class Error(val error : String) : MovieUiState
    object Loading : MovieUiState
}