package com.itacademy.ads.domain.usecase

import com.itacademy.ads.domain.repository.FavoritesRepository
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {

    suspend fun getFavorites(): Flow<Resource<List<Ad>?>> {
        return favoritesRepository.getFavorites()
    }

    suspend fun addFavorite(adId: Long) {
        favoritesRepository.addFavorite(adId)
    }

    suspend fun deleteFavorite(adId: Long) {
        favoritesRepository.deleteFavorite(adId)
    }


    suspend fun getFavoritesId(): Flow<Resource<List<Long>?>> {
        return favoritesRepository.getFavoritesId()
    }

    suspend fun deleteFavorites() {
        favoritesRepository.deleteFavorites()
    }

    suspend fun deleteInactiveFavorites() {
        favoritesRepository.deleteInactiveFavorites()
    }
}