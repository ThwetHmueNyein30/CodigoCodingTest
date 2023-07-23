package com.codigo.codetest.code.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.data.repo.MovieRepo
import com.codigo.codetest.code.data.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repo: MovieRepo,
) : ViewModel() {

    val popularMovieUiState: StateFlow<MovieUiState> =
        getPopularMovies().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieUiState.Loading,
        )

    val upComingMovieUiState: StateFlow<MovieUiState> =
        getUpcomingMovies().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieUiState.Loading,
        )


    private fun getPopularMovies(): Flow<MovieUiState> {

        return repo.getPopularMovies().map { response ->
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

    private fun getUpcomingMovies(): Flow<MovieUiState> {
        return repo.getUpcomingMovies().map { response ->
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

    fun onTapFavorite(movie: Movie) {
        viewModelScope.launch {
            repo.onSaveFavoriteMovie(movie.id,!movie.isFavorite)
        }
    }
}


sealed interface MovieUiState {
    data class Success(val data: List<Movie>) : MovieUiState
    data class Error(val error: String) : MovieUiState
    object Loading : MovieUiState
}