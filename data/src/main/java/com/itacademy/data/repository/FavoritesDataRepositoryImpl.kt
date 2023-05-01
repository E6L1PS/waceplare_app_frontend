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
import javax.inject.Inject

class FavoritesDataRepositoryImpl @Inject constructor(
    private val api: FavoritesApi
) : FavoritesDataRepository {

    override suspend fun getFavorites(): Flow<Resource<List<Ad>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getFavoriteAds()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
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
        api.addFavoriteAd(adId)
    }

    override suspend fun deleteFavorite(adId: Long) {
        api.deleteFavoriteAd(adId)
    }

    override suspend fun deleteFavorites() {
        api.deleteFavorites()
    }

    override suspend fun deleteInactiveFavorites() {
        api.deleteInactiveFavorites()
    }

}