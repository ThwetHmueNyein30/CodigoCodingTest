package com.codigo.codetest.code.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.R
import com.google.gson.Gson
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.presentation.adapter.PopularMovieAdapter
import com.codigo.codetest.code.presentation.adapter.UpComingMovieAdapter
import com.codigo.codetest.code.util.setVisible
import com.codigo.codetest.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var upComingMovieAdapter: UpComingMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        context ?: return binding.root

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observePopularMovies()
        observeUpComingMovies()
    }

    private fun observeUpComingMovies() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.upComingMovieUiState.collectLatest { uiState ->
                    with(binding) {
                        when (uiState) {
                            is MovieUiState.Loading -> {
                                tvUpcomingError.setVisible(false)
                                rvUpcomingMovies.setVisible(false)
                                upcomingProgressBar.setVisible(true)
                            }

                            is MovieUiState.Success -> {
                                tvUpcomingError.setVisible(false)
                                rvUpcomingMovies.setVisible(true)
                                upcomingProgressBar.setVisible(false)
                                upComingMovieAdapter.submitList(uiState.data)
                            }

                            is MovieUiState.Error -> {
                                tvUpcomingError.setVisible(true)
                                rvUpcomingMovies.setVisible(false)
                                upcomingProgressBar.setVisible(false)
                                tvUpcomingError.text = uiState.error
                            }
                        }

                    }
                }
            }
        }

    }

    private fun observePopularMovies() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.popularMovieUiState.collectLatest { uiState ->
                    with(binding) {
                        when (uiState) {
                            is MovieUiState.Loading -> {
                                tvPopularError.setVisible(false)
                                rvPopularMovies.setVisible(false)
                                popularProgressBar.setVisible(true)
                            }

                            is MovieUiState.Success -> {
                                tvPopularError.setVisible(false)
                                rvPopularMovies.setVisible(true)
                                popularProgressBar.setVisible(false)
                                popularMovieAdapter.submitList(uiState.data)
                            }

                            is MovieUiState.Error -> {
                                tvPopularError.setVisible(true)
                                rvPopularMovies.setVisible(false)
                                popularProgressBar.setVisible(false)
                                tvUpcomingError.text = uiState.error
                            }
                        }

                    }
                }
            }
        }

    }

    private fun setupUI() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        popularMovieAdapter = PopularMovieAdapter(onClick = { navigateToDetail(it) },
            onFavoriteClick = { viewModel.onTapFavorite(it) })
        upComingMovieAdapter = UpComingMovieAdapter(onClick = { navigateToDetail(it) },
            onFavoriteClick = { viewModel.onTapFavorite(it) })
        binding.rvPopularMovies.adapter = popularMovieAdapter
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(context)
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        binding.rvUpcomingMovies.adapter = upComingMovieAdapter
        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(context)
        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            R.id.action_movieFragment_to_movieDetailFragment,
            bundleOf("movie" to Gson().toJson(movie))
        )
    }

}