package com.itacademy.profile.domain.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserInfo(): Flow<Resource<UserInfo?>>

}