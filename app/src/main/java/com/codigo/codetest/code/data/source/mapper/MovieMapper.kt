package com.codigo.codetest.code.data.source.mapper


import com.codigo.codetest.code.data.source.movie.Movie
import com.codigo.codetest.code.util.reformatDate
import com.codigo.codetest.code.data.source.local.MovieEntity
import com.thn.codigocodetest.data.source.remote.list.MovieDto


fun MovieDto.toMovieEntity(page:Int,type:String): MovieEntity {
    return MovieEntity(
        id = this.id,
        page=page,
        popularity = this.popularity,
        voteAverage = this.vote_average,
        voteCount = this.vote_count,
        image = (this.poster_path ?: this.backdrop_path)?.toImageUrl(),
        title = this.title ?: this.original_title ?: "No title found",
        overview = this.overview ?: "No overview found",
        releaseDate = this.release_date.reformatDate(),
        originalLanguage = this.original_language,
        isVideo = this.video,
        type= type
    )
}


fun MovieEntity.toMovie() : Movie {
    return Movie(
        id = this.id,
        page= this.page,
        popularity = this.popularity,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        image = this.image,
        title = this.title ,
        overview = this.overview ,
        releaseDate = this.releaseDate,
        originalLanguage = this.originalLanguage,
        isVideo = this.isVideo,
        type= this.type,
        isFavorite= this.isFavorite
    )
}





private fun String.toImageUrl(): String {
    return "https://image.tmdb.org/t/p/w500$this"
}

private fun Int.durationToString(): String {
    val hour = this / 60
    val minute = this % 60
    var appendText = ""
    if (hour > 0) appendText += "$hour hr"
    if (minute > 0) appendText += "$minute mins"
    return appendText
}