package com.itacademy.favorites.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend fun getFavorites(): Flow<Resource<List<Ad>?>> {
        return favoritesRepository.getFavorites()
    }

    suspend fun deleteFavorites() {
        favoritesRepository.deleteFavorites()
    }

    suspend fun deleteInactiveFavorites() {
        favoritesRepository.deleteInactiveFavorites()
    }
}