package com.codigo.codetest.code.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.codigo.codetest.code.data.source.movie.Movie

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
