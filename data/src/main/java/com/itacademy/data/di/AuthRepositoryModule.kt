package com.itacademy.data.di

import com.itacademy.data.AdsDataRepository
import com.itacademy.data.AuthDataRepository
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.data.repository.AdsDataRepositoryImpl
import com.itacademy.data.repository.AuthDataDataRepositoryImpl
import com.itacademy.data.repository.PersonalAdsDataDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(
        authDataDataRepositoryImpl: AuthDataDataRepositoryImpl
    ): AuthDataRepository


    @Binds
    @Singleton
    fun bindAdsRepository(
        adsDataRepositoryImpl: AdsDataRepositoryImpl
    ): AdsDataRepository


    @Binds
    @Singleton
    fun bindPersonalAdsRepository(
        adsDataDataRepositoryImpl: PersonalAdsDataDataRepositoryImpl
    ): PersonalAdsDataRepository
}