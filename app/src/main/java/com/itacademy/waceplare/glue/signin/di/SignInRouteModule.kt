package com.itacademy.waceplare.glue.signin.di

import com.itacademy.sign_in.presentation.SignInRouter
import com.itacademy.waceplare.glue.signin.AdapterSignInRoute
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface SignInRouteModule {

    @Binds
    fun bindSignInRouter(router: AdapterSignInRoute): SignInRouter

}