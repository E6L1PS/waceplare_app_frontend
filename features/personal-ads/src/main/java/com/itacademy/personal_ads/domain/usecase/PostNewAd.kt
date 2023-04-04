package com.itacademy.personal_ads.domain.usecase

import com.itacademy.common.model.AdDTO
import com.itacademy.personal_ads.PersonalAdsRepository
import javax.inject.Inject

class PostNewAd @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {
    suspend fun postAd(adDTO: AdDTO) = personalAdsRepository.postAd(adDTO)
}