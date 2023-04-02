package com.itacademy.data.api

import com.itacademy.data.model.Ad
import com.itacademy.data.model.AdDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AdsApi {

    @GET("ads/my")
    suspend fun getAds(): Response<List<Ad>>

    @POST("ads")
    suspend fun postAd(@Body ad: AdDTO)



}