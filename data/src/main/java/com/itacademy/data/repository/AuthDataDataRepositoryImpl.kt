package com.itacademy.data.repository

import android.content.SharedPreferences
import com.itacademy.common.Resource
import com.itacademy.data.AuthDataRepository
import com.itacademy.data.api.AuthApi
import com.itacademy.data.util.Constants
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest
import retrofit2.HttpException
import javax.inject.Inject

class AuthDataDataRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthDataRepository {

    override suspend fun signUp(registerRequest: RegisterRequest): Resource<Unit> {
        return try {
            val response = api.signUp(registerRequest)

            if (response.isSuccessful) {
                sharedPreferences.edit()
                    .putString(Constants.JWT_KEY, response.body()?.token)
                    .apply()
                Resource.success(Unit)
            } else {
                Resource.error(response.message(), null)
            }

        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {

                Resource.error(e.message(), null)
            } else {
                Resource.error(e.message(), null)
            }
        } catch (e: Exception) {
            Resource.error(e.toString(), null)
        }
    }

    override suspend fun signIn(authenticationRequest: AuthenticationRequest): Resource<Unit> {
        return try {
            val response = api.signIn(authenticationRequest)
            sharedPreferences.edit()
                .putString(Constants.JWT_KEY, response.body()?.token)
                .apply()

            if (response.isSuccessful) {

                Resource.success(Unit)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {

                Resource.error(e.message(), null)
            } else {
                Resource.error(e.message(), null)
            }
        } catch (e: Exception) {
            Resource.error(e.toString(), null)
        }
    }

    override suspend fun isAuthenticated(): Resource<Unit> {
        return try {
            val response = api.isAuthenticated()

            if (response.isSuccessful) {
                Resource.success(Unit)
            } else {
                Resource.error(response.message(), null)
            }
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {

                Resource.error(e.message(), null)
            } else {
                Resource.error(e.message(), null)
            }
        } catch (e: Exception) {
            Resource.error(e.toString(), null)
        }
    }

    override suspend fun logOut() {
        sharedPreferences.edit()
            .putString(Constants.JWT_KEY, null)
            .apply()
    }
}