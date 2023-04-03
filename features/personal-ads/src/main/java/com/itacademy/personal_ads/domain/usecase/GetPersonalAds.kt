package com.itacademy.personal_ads.domain.usecase

import com.itacademy.personal_ads.PersonalAdsRepository
import javax.inject.Inject

class GetPersonalAds @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {
    suspend fun getAds() = personalAdsRepository.getAds()
}