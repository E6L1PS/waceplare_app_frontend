package com.itacademy.search.domain.usecase

import com.itacademy.search.AdsRepository
import javax.inject.Inject

class SearchAdsByTitleUseCase @Inject constructor(
    private val adsRepository: AdsRepository
) {
   suspend fun searchByTitle(title: String?) = adsRepository.getAds(title)
}