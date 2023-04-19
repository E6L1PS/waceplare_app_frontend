package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import kotlinx.coroutines.flow.Flow

interface FavoritesDataRepository {

    suspend fun getFavorites(): Flow<Resource<List<Ad>?>>

    suspend fun getFavoritesId(): Flow<Resource<List<Long>?>>

    suspend fun addFavorite(adId: Long)

    suspend fun deleteFavorites(adId: Long)


}
