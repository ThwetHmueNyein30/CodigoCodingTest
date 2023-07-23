package com.codigo.codetest.code.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.databinding.UpcomingMovieListItemBinding

class UpComingMovieAdapter(val onClick: (Movie) -> Unit, val onFavoriteClick:(Movie)->Unit) :
    ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            UpcomingMovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position), position)
    }


    inner class MovieViewHolder(private val binding: UpcomingMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, position: Int) {
            if (position == 0) {
                binding.movieCardView.setPadding(4, 4, 4, 4)
            } else {
                binding.movieCardView.setPadding(0, 4, 4, 4)

            }
            binding.movie = movie
            binding.movieCardView.setOnClickListener {
                onClick(movie)
            }
            binding.imgFavorite.setOnClickListener {
                onFavoriteClick(movie)
            }
        }
    }

}


