package com.itacademy.waceplare.glue.favorites

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.data.FavoritesDataRepository
import com.itacademy.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterFavoritesRepository @Inject constructor(
    private val favoritesDataRepository: FavoritesDataRepository
): FavoritesRepository {
    override suspend fun getFavorites(): Flow<Resource<List<Ad>?>> {
        return favoritesDataRepository.getFavorites()
    }

    override suspend fun addFavorite(adId: Long) {
        favoritesDataRepository.addFavorite(adId)
    }

    override suspend fun deleteFavorite(adId: Long) {
        favoritesDataRepository.deleteFavorite(adId)
    }

    override suspend fun deleteFavorites() {
        favoritesDataRepository.deleteFavorites()
    }

    override suspend fun deleteInactiveFavorites() {
        favoritesDataRepository.deleteInactiveFavorites()
    }


}
