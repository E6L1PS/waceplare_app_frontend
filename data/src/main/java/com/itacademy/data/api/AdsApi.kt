package com.itacademy.data.api

import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface AdsApi {

    @GET("ads/my")
    suspend fun getAds(): Response<List<Ad>>

    @POST("ads")
    suspend fun postAd(@Body ad: AdDTO): Response<Unit>

    @DELETE("ads/my/{adId}")
    suspend fun deleteAd(@Path("adId") adId: Long): Response<Unit>

    @PUT("ads/my/{adId}/hide")
    suspend fun hideAd(@Path("adId") adId: Long): Response<Unit>

    @PUT("ads/my/{adId}/show")
    suspend fun showAd(@Path("adId") adId: Long): Response<Unit>


    @Multipart
    @POST("{adId}/images")
    suspend fun uploadImages(
        @Path("adId") adId: Long,
        @Part images: List<MultipartBody.Part>
    ): Response<Unit>

}