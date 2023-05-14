package com.itacademy.ads.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.ads.domain.repository.AdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchAdsByTitleUseCase @Inject constructor(
    private val adsRepository: AdsRepository
) {
   suspend fun searchByTitle(title: String?): Flow<Resource<List<Ad>?>> = adsRepository.getAds(title)
}