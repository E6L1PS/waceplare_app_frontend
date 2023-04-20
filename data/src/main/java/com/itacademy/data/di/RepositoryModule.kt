package com.itacademy.data.di

import com.itacademy.data.*
import com.itacademy.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

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
    fun bindFavoritesRepository(
        favoritesDataRepositoryImpl: FavoritesDataRepositoryImpl
    ): FavoritesDataRepository

    @Binds
    @Singleton
    fun bindPersonalAdsRepository(
        adsDataDataRepositoryImpl: PersonalAdsDataRepositoryImpl
    ): PersonalAdsDataRepository

    @Binds
    @Singleton
    fun bindUserRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl
    ): UserDataRepository
}