package com.itacademy.waceplare.di

import com.itacademy.data.AdsDataRepository
import com.itacademy.data.AuthDataRepository
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.data.repository.AdsDataRepositoryImpl
import com.itacademy.data.repository.AuthDataDataRepositoryImpl
import com.itacademy.data.repository.PersonalAdsDataDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideAuthRepository(authDataRepositoryImpl: AuthDataDataRepositoryImpl): AuthDataRepository = authDataRepositoryImpl

    @Provides
    fun provideMyAdRepository(myAdsDataRepositoryImpl: PersonalAdsDataDataRepositoryImpl): PersonalAdsDataRepository = myAdsDataRepositoryImpl

    @Provides
    fun provideAdRepository(adsDataRepositoryImpl: AdsDataRepositoryImpl): AdsDataRepository = adsDataRepositoryImpl

}
