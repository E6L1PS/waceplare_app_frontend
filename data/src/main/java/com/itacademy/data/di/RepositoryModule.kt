/*
package com.itacademy.data.di

import com.itacademy.data.AdsDataRepository
import com.itacademy.data.AuthDataRepository
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.data.repository.AdsDataRepositoryImpl
import com.itacademy.data.repository.AuthDataDataRepositoryImpl
import com.itacademy.data.repository.PersonalAdsDataDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideAuthRepository(authDataRepositoryImpl: AuthDataDataRepositoryImpl): AuthDataRepository =
        authDataRepositoryImpl

    @Binds
    @Singleton
    fun provideMyAdRepository(personalAdsDataDataRepositoryImpl: PersonalAdsDataDataRepositoryImpl): PersonalAdsDataRepository =
        personalAdsDataDataRepositoryImpl

    @Binds
    @Singleton
    fun provideAdRepository(adsRepositoryImpl: AdsDataRepositoryImpl): AdsDataRepository =
        adsRepositoryImpl

}
*/
