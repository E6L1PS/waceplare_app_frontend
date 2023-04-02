package com.itacademy.waceplare.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.data.model.Ad
import com.itacademy.waceplare.domain.model.AdDTO
import com.itacademy.waceplare.domain.repository.MyAdsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAdsViewModel @Inject constructor(
    private val myAdsRepository: MyAdsRepository
) : ViewModel() {

    private val _ads = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val ads: StateFlow<Resource<List<Ad>?>> = _ads.asStateFlow()


    init {
        getAds()
    }

    fun getAds() {
        viewModelScope.launch {
            _ads.emitAll(myAdsRepository.getAds())
        }
    }

    fun postAd(ad: AdDTO) {
        viewModelScope.launch {
            myAdsRepository.postAd(ad)
        }
    }
}