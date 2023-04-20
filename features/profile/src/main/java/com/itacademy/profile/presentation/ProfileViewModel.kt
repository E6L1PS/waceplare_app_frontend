package com.itacademy.profile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import com.itacademy.profile.domain.usecase.GetUserInfoUseCase
import com.itacademy.profile.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _isAuthenticated =  MutableStateFlow<Resource<Unit>>(Resource.loading(null))
    val isAuthenticated: StateFlow<Resource<Unit>> = _isAuthenticated.asStateFlow()


    private val _userInfo =  MutableStateFlow<Resource<UserInfo?>>(Resource.loading(null))
    val userInfo: StateFlow<Resource<UserInfo?>> = _userInfo.asStateFlow()

    init {
        isAuthenticated()
        getUserInfo()
    }

    private fun isAuthenticated() {

        Log.d("AuthTEST", "called")

        viewModelScope.launch {
            _isAuthenticated.emit(
                when (val result = logOutUseCase.isAuthenticated()) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message)
                    is Resource.Loading -> Resource.Loading(Unit)
                }
            )
        }


    }

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase.logOut()
        }
        isAuthenticated()
    }

    fun getUserInfo() {
        viewModelScope.launch {
            _userInfo.emitAll(getUserInfoUseCase.getUserInfoUseCase())
        }
    }

}
