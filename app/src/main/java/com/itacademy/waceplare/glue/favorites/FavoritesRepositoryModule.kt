package com.itacademy.waceplare.glue.favorites

import com.itacademy.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(adapterFavoritesRepository: AdapterFavoritesRepository): FavoritesRepository
}