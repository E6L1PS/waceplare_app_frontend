package com.itacademy.personal_ads.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import com.itacademy.personal_ads.domain.usecase.GetPersonalAds
import com.itacademy.personal_ads.domain.usecase.PostNewAd
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalAdsViewModel @Inject constructor(
    private val getPersonalAds: GetPersonalAds,
    private val postNewAd: PostNewAd
) : ViewModel() {

    private val _ads = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val ads: StateFlow<Resource<List<Ad>?>> = _ads.asStateFlow()


    init {
        getAds()
    }

    fun getAds() {
        viewModelScope.launch {
            _ads.emitAll(getPersonalAds.getAds())
        }
    }

    fun postAd(ad: AdDTO) {
        viewModelScope.launch {
            postNewAd.postAd(ad)
        }
    }
}