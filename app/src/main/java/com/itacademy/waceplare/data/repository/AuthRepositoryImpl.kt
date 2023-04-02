package com.itacademy.waceplare.data.repository

import android.content.SharedPreferences
import com.itacademy.data.api.AuthApi
import com.itacademy.data.model.auth.AuthResult
import com.itacademy.data.model.auth.AuthenticationRequest
import com.itacademy.data.model.auth.RegisterRequest
import com.itacademy.waceplare.domain.repository.AuthRepository
import com.itacademy.data.util.Constants
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
): AuthRepository {

     override suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit> {
        return try {
            val response = api.signUp(registerRequest)
            sharedPreferences.edit()
                .putString("jwt_token", response.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

     override suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit> {
        return try {
            val response = api.signIn(authenticationRequest)

            sharedPreferences.edit()
                .putString(Constants.JWT_KEY, response.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

     override suspend fun isAuthenticated(): AuthResult<Unit> {
        return try {
            if (api.isAuthenticated()) {
                AuthResult.Authorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}