package com.itacademy.data.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import com.itacademy.data.UserDataRepository
import com.itacademy.data.api.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserDataRepository {
    override suspend fun getUserInfo(): Flow<Resource<UserInfo?>> = flow {
        emit(Resource.loading(null))

        val response = api.getUserInfo()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)
}