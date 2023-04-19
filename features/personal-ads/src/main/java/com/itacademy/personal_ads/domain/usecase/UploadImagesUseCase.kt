package com.itacademy.personal_ads.domain.usecase

import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import java.io.File
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {

    suspend fun uploadImages(adId: Long, images: List<File>) = personalAdsRepository.uploadImages(adId, images)
}
