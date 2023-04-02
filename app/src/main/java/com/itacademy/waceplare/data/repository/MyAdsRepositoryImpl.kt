package com.itacademy.waceplare.data.repository

import com.itacademy.data.api.AdsApi
import com.itacademy.data.model.Ad
import com.itacademy.waceplare.domain.model.AdDTO
import com.itacademy.waceplare.domain.repository.MyAdsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MyAdsRepositoryImpl @Inject constructor(
    private val api: AdsApi
) : MyAdsRepository {

    override suspend fun getAds(): Flow<Resource<List<Ad>?>> =  flow {
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