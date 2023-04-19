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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class PersonalAdsDataRepositoryImpl @Inject constructor(
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
        val response = api.postAd(ad)
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }

    override suspend fun deleteAd(adId: Long) {
        val response = api.deleteAd(adId)
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }

    override suspend fun hideAd(adId: Long) {
        val response = api.hideAd(adId)
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }

    override suspend fun showAd(adId: Long) {
        val response = api.showAd(adId)
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }

    override suspend fun uploadImages(adId: Long, images: List<File>) {
        val imageParts = images.map { file ->
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("images", file.name, requestBody)
        }
        val response = api.uploadImages(adId, images = imageParts)
        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }


}