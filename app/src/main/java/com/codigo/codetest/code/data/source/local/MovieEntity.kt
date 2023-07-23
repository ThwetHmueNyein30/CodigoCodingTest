package com.codigo.codetest.code.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movie",
)
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val page :Int,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val image: String?,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val originalLanguage: String,
    val isVideo: Boolean,
    val type : String,
    val isFavorite : Boolean = false
)

enum class MovieType {
    UPCOMING,
    POPULAR
}
