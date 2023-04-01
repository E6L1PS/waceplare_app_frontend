package com.itacademy.waceplare.ui.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.waceplare.data.model.auth.AuthResult
import com.itacademy.waceplare.data.model.auth.AuthenticationRequest
import com.itacademy.waceplare.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepositoryImpl): ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    init {
        isAuthorized()
    }


    fun isAuthorized() {

        viewModelScope.launch {
            _isAuthenticated.emit(
                when (repository.isAuthenticated()) {
                    is AuthResult.Authorized -> true
                    else -> false
                }
            )
        }
    }

    fun signIn(authenticationRequest: AuthenticationRequest) {
        viewModelScope.launch {
            _isAuthenticated.emit(
                when (repository.signIn(authenticationRequest)) {
                    is AuthResult.Authorized -> true
                    else -> false
                }
            )
        }

    }

}