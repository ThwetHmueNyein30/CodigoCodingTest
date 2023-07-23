package com.codigo.codetest.code.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codigo.codetest.code.data.source.local.MovieEntity

@Database(
    version = 1,
    exportSchema = true,
    entities = [MovieEntity::class]
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}