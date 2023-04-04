package com.itacademy.waceplare.glue.personal_ads

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.personal_ads.PersonalAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterPersonalAdsRepository @Inject constructor(
    private val personalAdsDataRepository: PersonalAdsDataRepository
) : PersonalAdsRepository {

    override suspend fun getAds(): Flow<Resource<List<Ad>?>> {
        return personalAdsDataRepository.getAds()
    }

    override suspend fun postAd(ad: AdDTO) {
        personalAdsDataRepository.postAd(ad)
    }

}
