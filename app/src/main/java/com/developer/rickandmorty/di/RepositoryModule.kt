package com.developer.rickandmorty.di

import com.developer.rickandmorty.features.data.repository.RickAndMortyRepositoryImpl
import com.developer.rickandmorty.features.domain.repository.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRickAndMortyRepository(
        rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl
    ): RickAndMortyRepository
} 