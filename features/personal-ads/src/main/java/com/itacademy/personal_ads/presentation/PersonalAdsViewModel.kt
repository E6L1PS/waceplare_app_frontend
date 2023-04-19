package com.itacademy.personal_ads.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.personal_ads.domain.model.AdDTO
import com.itacademy.personal_ads.domain.usecase.GetPersonalAdsUseCase
import com.itacademy.personal_ads.domain.usecase.PostNewAdUseCase
import com.itacademy.personal_ads.domain.usecase.UploadImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PersonalAdsViewModel @Inject constructor(
    private val getPersonalAdsUseCase: GetPersonalAdsUseCase,
    private val postNewAdUseCase: PostNewAdUseCase,
    private val uploadImagesUseCase: UploadImagesUseCase,
) : ViewModel() {

    private val _ads = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val ads: StateFlow<Resource<List<Ad>?>> = _ads.asStateFlow()

    init {
        getAds()
    }

    fun getAds() {
        viewModelScope.launch {
            _ads.emitAll(getPersonalAdsUseCase.getAds())
        }
    }

    fun postAd(ad: AdDTO) {
        viewModelScope.launch {
            postNewAdUseCase.postAd(ad)
        }
    }

    fun uploadImages(adId: Long, images: List<File>) {
        viewModelScope.launch {
            uploadImagesUseCase.uploadImages(adId, images)
        }
    }
}