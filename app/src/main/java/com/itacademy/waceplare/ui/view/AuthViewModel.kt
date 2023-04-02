package com.itacademy.waceplare.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.data.model.auth.AuthResult
import com.itacademy.data.model.auth.AuthenticationRequest
import com.itacademy.data.repository.AuthDataDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthDataDataRepositoryImpl): ViewModel() {

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