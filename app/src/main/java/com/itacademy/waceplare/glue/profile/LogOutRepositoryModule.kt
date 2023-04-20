package com.itacademy.waceplare.glue.profile

import com.itacademy.profile.domain.repository.AuthRepository
import com.itacademy.profile.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LogOutRepositoryModule {

    @Binds
    fun bindAuthRepository(authRepository: AdapterAuthRepository): AuthRepository

    @Binds
    fun bindUserRepository(userRepository: AdapterUserRepository): UserRepository

}