package com.itacademy.personal_ads.domain.usecase

import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val personalAdsRepository: PersonalAdsRepository
) {

    suspend fun uploadImages(adId: Long, images: List<ByteArray?>) = personalAdsRepository.uploadImages(adId, images)
}
