package com.itacademy.ads.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.ads.domain.usecase.GetInfoAboutAdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getInfoAboutAdUseCase: GetInfoAboutAdUseCase
) : ViewModel() {

    private val _ad = MutableStateFlow<Resource<Ad?>>(Resource.loading(null))
    val ad: StateFlow<Resource<Ad?>> = _ad.asStateFlow()

    init {
        savedStateHandle.get<String>("id")?.toLongOrNull()?.let { getAd(it) }
    }

    private fun getAd(id: Long) {
        Log.d("getAd", id.toString())
        viewModelScope.launch {
            _ad.emit(getInfoAboutAdUseCase.getAd(id))
        }
    }

}