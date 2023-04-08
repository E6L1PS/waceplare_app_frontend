package com.itacademy.profile.domain.repository

import com.itacademy.common.Resource

interface AuthRepository {
    suspend fun isAuthenticated(): Resource<Unit>
    suspend fun logOut()
}
