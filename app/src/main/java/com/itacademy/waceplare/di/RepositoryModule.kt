package com.itacademy.waceplare.di

import com.itacademy.waceplare.data.repository.AdsRepositoryImpl
import com.itacademy.waceplare.data.repository.AuthRepositoryImpl
import com.itacademy.waceplare.data.repository.MyAdsRepositoryImpl
import com.itacademy.waceplare.domain.repository.AdsRepository
import com.itacademy.waceplare.domain.repository.AuthRepository
import com.itacademy.waceplare.domain.repository.MyAdsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl

    @Provides
    fun provideMyAdRepository(myAdsRepositoryImpl: MyAdsRepositoryImpl): MyAdsRepository = myAdsRepositoryImpl

    @Provides
    fun provideAdRepository(adsRepositoryImpl: AdsRepositoryImpl): AdsRepository = adsRepositoryImpl

}
