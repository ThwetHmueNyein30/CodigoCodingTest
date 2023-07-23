package com.codigo.codetest.code.di

import android.content.Context
import androidx.room.Room
import com.codigo.codetest.code.data.db.MovieDao
import com.codigo.codetest.code.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "movie-database"
    ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideDao(database: MovieDatabase): MovieDao = database.movieDao()
}