package com.itacademy.data.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import com.itacademy.data.PersonalAdsDataRepository
import com.itacademy.data.api.AdsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonalAdsDataDataRepositoryImpl @Inject constructor(
    private val api: AdsApi
) : PersonalAdsDataRepository {

    override suspend fun getAds(): Flow<Resource<List<Ad>?>> = flow {
        emit(Resource.loading(null))

        val response = api.getAds()

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)


    override suspend fun postAd(ad: AdDTO) {
        api.postAd(ad)
    }

}