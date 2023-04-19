package com.itacademy.waceplare.glue.personal_ads

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.StateAd
import com.itacademy.common.model.TypeAd
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import com.itacademy.personal_ads.domain.model.AdDTO
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class AdapterPersonalAdsRepository @Inject constructor(
    private val personalAdsDataRepository: PersonalAdsDataRepository
) : PersonalAdsRepository {

    override suspend fun getAds(): Flow<Resource<List<Ad>?>> {
        return personalAdsDataRepository.getAds()
    }

    override suspend fun postAd(ad: AdDTO) {
        personalAdsDataRepository.postAd(com.itacademy.common.model.AdDTO(
            price = ad.price,
            title = ad.title,
            description = ad.description,
            type = TypeAd.valueOf(ad.type.toString()),
            state = ad.state?.let { StateAd.valueOf(it.name) } ?: null
        ))
    }

    override suspend fun uploadImages(adId: Long, images: List<File>) {
        return personalAdsDataRepository.uploadImages(adId, images)
    }

}
