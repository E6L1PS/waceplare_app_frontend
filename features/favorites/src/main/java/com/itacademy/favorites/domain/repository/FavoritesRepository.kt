package com.itacademy.favorites.domain.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavorites(): Flow<Resource<List<Ad>?>>

    suspend fun addFavorite(adId: Long)

    suspend fun deleteFavorite(adId: Long)

    suspend fun deleteFavorites()

    suspend fun deleteInactiveFavorites()
}
