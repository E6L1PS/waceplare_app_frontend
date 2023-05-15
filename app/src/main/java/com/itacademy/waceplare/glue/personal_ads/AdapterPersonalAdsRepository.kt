package com.itacademy.waceplare.glue.personal_ads

import android.util.Log
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import com.itacademy.common.model.StateAd
import com.itacademy.common.model.TypeAd
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.personal_ads.domain.repository.PersonalAdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterPersonalAdsRepository @Inject constructor(
    private val personalAdsDataRepository: PersonalAdsDataRepository,
) : PersonalAdsRepository {

    override suspend fun getAds(): Flow<Resource<List<Ad>?>> {
        return personalAdsDataRepository.getAds()
    }

    override suspend fun postAd(ad: AdDTO): Resource<Long?> {
        return personalAdsDataRepository.postAd(ad)
    }

    override suspend fun uploadImages(adId: Long, images: List<ByteArray?>) {

        Log.d("bytes___", images.toString())
        return personalAdsDataRepository.uploadImages(adId, images)
    }

    override suspend fun postAdWithImages(adDTO: AdDTO, images: List<ByteArray?>) {
        personalAdsDataRepository.postAdWithImages(adDTO, images)
    }

}
