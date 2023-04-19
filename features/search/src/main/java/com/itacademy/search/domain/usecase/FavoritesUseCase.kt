package com.itacademy.search.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.search.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
) {

    suspend fun getFavorites(): Flow<Resource<List<Long>?>> {
        return favoritesRepository.getFavoritesId()
    }

    suspend fun addFavorite(adId: Long) {
        favoritesRepository.addFavorite(adId)
    }

    suspend fun deleteFavorite(adId: Long) {
        favoritesRepository.deleteFavorite(adId)
    }

}