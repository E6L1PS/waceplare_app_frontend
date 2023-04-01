package com.itacademy.waceplare.domain.repository

import com.itacademy.waceplare.data.model.Ad
import com.itacademy.waceplare.domain.model.AdDTO
import com.itacademy.waceplare.util.Resource
import kotlinx.coroutines.flow.Flow

interface MyAdsRepository {

    suspend fun getAds(): Flow<Resource<List<Ad>?>>

    suspend fun postAd(ad: AdDTO)

}
