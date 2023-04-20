package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    suspend fun getUserInfo(): Flow<Resource<UserInfo?>>
}