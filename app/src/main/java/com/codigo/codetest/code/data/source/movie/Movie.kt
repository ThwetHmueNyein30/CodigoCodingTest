package com.codigo.codetest.code.data.source.movie

data class Movie(
    val id: Int,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val image: String?,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val originalLanguage: String,
    val isVideo: Boolean,
    val type:String,
    val isFavorite : Boolean
)
