package com.itacademy.waceplare.glue.auth.di

import com.itacademy.auth.domain.repository.AuthRepository
import com.itacademy.waceplare.glue.auth.repository.AdapterAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignInRepositoryModule {

    @Binds
    fun bindAuthRepository(adapterAuthRepository: AdapterAuthRepository): AuthRepository

}