package com.itacademy.data.api

import com.itacademy.common.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUserInfo(): Response<UserInfo>

}