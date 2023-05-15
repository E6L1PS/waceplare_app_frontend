package com.itacademy.data.repository

import android.util.Log
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
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
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


    override suspend fun postAd(ad: AdDTO): Resource<Long?> {
        val response = api.postAd(ad)
        return if (response.isSuccessful) {
            Log.d("ADDDDDDDDID", response.body().toString())
            Resource.success(response.body())
        } else {
            Log.d("ADDDDDDDDID", "itsnotok")
            Resource.error(response.message(), null)
        }
    }

    override suspend fun postAdWithImages(ad: AdDTO, images: List<ByteArray?>) {
        val imageParts = images.map { bytes ->
            createMultipartBodyPart(bytes)
        }

        val response = api.postAdWithImages(ad, imageParts)
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

    override suspend fun uploadImages(adId: Long, images: List<ByteArray?>) {
        Log.d("list____a", images.toString())
        val imageParts = images.map { bytes ->
            createMultipartBodyPart(bytes)
        }

        val response = api.uploadImages(adId, files = imageParts)

        if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(response.message(), null)
        }
    }

    private suspend fun createMultipartBodyPart(bytes: ByteArray?): MultipartBody.Part =
        withContext(Dispatchers.IO) {
            val requestBody = bytes?.toRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("files", "image", requestBody!!)
        }


}