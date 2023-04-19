package com.itacademy.waceplare.glue.personal_ads

import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PersonalAdsRepositoryModule {
    @Binds
    fun bindPersonalAdsRepository(adapterPersonalAdsRepository: AdapterPersonalAdsRepository): PersonalAdsRepository
}