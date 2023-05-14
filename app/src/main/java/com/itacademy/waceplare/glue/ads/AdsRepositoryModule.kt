package com.itacademy.waceplare.glue.ads

import com.itacademy.ads.domain.repository.AdsRepository
import com.itacademy.ads.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdsRepositoryModule {
    @Binds
    fun bindAdsRepository(adapterAdsRepository: AdapterAdsRepository): AdsRepository

    @Binds
    fun bindFavoritesRepository(adapterFavoritesRepository: AdapterFavoritesRepository): FavoritesRepository
}