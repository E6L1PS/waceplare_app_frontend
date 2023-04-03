package com.itacademy.waceplare.glue.signin.di

import com.itacademy.sign_in.domain.repository.AuthRepository
import com.itacademy.waceplare.glue.signin.repository.AdapterAuthRepository
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