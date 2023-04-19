package com.itacademy.waceplare.glue.search

import com.itacademy.common.Resource
import com.itacademy.data.FavoritesDataRepository
import com.itacademy.search.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterFavoritesRepository @Inject constructor(
    private val favoritesDataRepository: FavoritesDataRepository
): FavoritesRepository {
    override suspend fun getFavoritesId(): Flow<Resource<List<Long>?>> {
        return favoritesDataRepository.getFavoritesId()
    }

    override suspend fun addFavorite(adId: Long) {
        favoritesDataRepository.addFavorite(adId)
    }

    override suspend fun deleteFavorite(adId: Long) {
        favoritesDataRepository.deleteFavorites(adId)
    }


}
