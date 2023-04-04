package com.itacademy.waceplare.glue.search

import com.itacademy.search.AdsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AdsRepositoryModule {
    @Binds
    fun bindAdsRepository(adapterAdsRepository: AdapterAdsRepository): AdsRepository
}