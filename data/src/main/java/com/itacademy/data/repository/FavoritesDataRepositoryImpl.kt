package com.itacademy.data.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.data.FavoritesDataRepository
import com.itacademy.data.api.FavoritesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

class FavoritesDataRepositoryImpl @Inject constructor(
    private val api: FavoritesApi,
) : FavoritesDataRepository {

    override suspend fun getFavorites(): Flow<Resource<List<Ad>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getFavoriteAds()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
                emit(Resource.error("Access denied", null))
            } else {
                emit(Resource.error(response.message(), null))
            }
        }

    }.catch { exception ->
        if (exception is HttpException && exception.code() == HttpURLConnection.HTTP_FORBIDDEN) {
            emit(Resource.error("Access denied", null))
        } else {
            emit(Resource.error(exception.message ?: "Error occurred", null))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFavoritesId(): Flow<Resource<List<Long>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getFavoriteAdsId()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)

    override suspend fun addFavorite(adId: Long) {
        try {
            api.addFavoriteAd(adId)
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

    override suspend fun deleteFavorite(adId: Long) {
        try {
            api.deleteFavoriteAd(adId)
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

    override suspend fun deleteFavorites() {
        try {
            api.deleteFavorites()
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

    override suspend fun deleteInactiveFavorites() {
        try {
            api.deleteInactiveFavorites()
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

}