package com.itacademy.personal_ads.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.common.model.AdDTO
import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostNewAdUseCase @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {
    suspend fun postAd(adDTO: AdDTO): Resource<Long?> = personalAdsRepository.postAd(adDTO)
}