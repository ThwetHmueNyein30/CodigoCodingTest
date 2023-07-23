package com.codigo.codetest.code.di

import com.codigo.codetest.code.data.repo.MovieRepo
import com.codigo.codetest.code.data.repo.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(repository : MovieRepoImpl) : MovieRepo
}