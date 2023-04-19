package com.itacademy.search.domain.repository

import com.itacademy.common.Resource
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavoritesId(): Flow<Resource<List<Long>?>>

    suspend fun addFavorite(adId: Long)

    suspend fun deleteFavorite(adId: Long)

}