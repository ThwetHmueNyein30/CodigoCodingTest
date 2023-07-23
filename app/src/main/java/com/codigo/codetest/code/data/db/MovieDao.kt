package com.codigo.codetest.code.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.codigo.codetest.code.data.source.local.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovie(entities: List<MovieEntity>)

    @Query("UPDATE movie SET isFavorite = :isFavourite WHERE id = :movieId")
    fun setFavouriteMovie(movieId : Int , isFavourite : Boolean)

    @Query(value = "SELECT * FROM movie WHERE page = :page AND type=:type ")
    fun getAllMovies(page: Int, type: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:id ORDER BY id")
    fun getMovieDetail(id: String): Flow<MovieEntity>

    @Query("DELETE FROM movie WHERE type=:type")
    suspend fun clearAllData(type: String)
}