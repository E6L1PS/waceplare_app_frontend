package com.itacademy.personal_ads.domain.usecase

import com.itacademy.common.model.AdDTO
import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository,
) {

    suspend fun uploadImages(adId: Long, images: List<ByteArray?>) =
        personalAdsRepository.uploadImages(adId, images)

    suspend fun postAdWithImages(adDTO: AdDTO, images: List<ByteArray?>) =
        personalAdsRepository.postAdWithImages(adDTO, images)

}
