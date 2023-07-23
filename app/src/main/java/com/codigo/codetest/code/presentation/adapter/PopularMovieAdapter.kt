package com.codigo.codetest.code.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.databinding.PopularMovieListItemBinding

class PopularMovieAdapter(val onClick: (Movie) -> Unit) : ListAdapter<Movie, RecyclerView.ViewHolder>(
    MovieDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            PopularMovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position))
    }


    inner class MovieViewHolder(private val binding: PopularMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
          binding.movie= movie
            binding.linearMovie.setOnClickListener {
                onClick(movie)
            }
        }
    }

}


