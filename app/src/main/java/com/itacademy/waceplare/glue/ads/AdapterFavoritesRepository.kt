package com.itacademy.waceplare.glue.ads

import com.itacademy.ads.domain.repository.FavoritesRepository
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.data.FavoritesDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterFavoritesRepository @Inject constructor(
    private val favoritesDataRepository: FavoritesDataRepository
) : FavoritesRepository {
    override suspend fun getFavoritesId(): Flow<Resource<List<Long>?>> {
        return favoritesDataRepository.getFavoritesId()
    }

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
