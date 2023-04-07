package com.itacademy.personal_ads.domain.usecase

import com.itacademy.personal_ads.PersonalAdsRepository
import com.itacademy.personal_ads.domain.model.AdDTO
import javax.inject.Inject

class PostNewAd @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {
    suspend fun postAd(adDTO: AdDTO) = personalAdsRepository.postAd(adDTO)
}