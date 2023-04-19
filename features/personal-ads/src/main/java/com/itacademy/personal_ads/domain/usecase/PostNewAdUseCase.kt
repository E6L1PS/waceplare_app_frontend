package com.itacademy.personal_ads.domain.usecase

import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import com.itacademy.personal_ads.domain.model.AdDTO
import javax.inject.Inject

class PostNewAdUseCase @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {
    suspend fun postAd(adDTO: AdDTO) = personalAdsRepository.postAd(adDTO)
}