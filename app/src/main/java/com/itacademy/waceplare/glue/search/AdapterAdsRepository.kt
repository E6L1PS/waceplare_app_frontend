package com.itacademy.waceplare.glue.search

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.data.AdsDataRepository
import com.itacademy.search.domain.repository.AdsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterAdsRepository @Inject constructor(
    private val adsDataRepository: AdsDataRepository
) : AdsRepository {
    override suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>> {
        return adsDataRepository.getAds(title)
    }

    override suspend fun getAd(id: Long): Resource<Ad?> {
        return adsDataRepository.getAd(id)
    }

}
