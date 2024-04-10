package com.tneagu.auth

import com.tneagu.auth.login.domain.LoginUseCase
import com.tneagu.auth.login.domain.LoginUseCaseImpl
import com.tneagu.data.repository.auth.AuthRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object AuthModule {

    @Provides
    fun providesLoginUseCase(authRepo: AuthRepo): LoginUseCase = LoginUseCaseImpl(authRepo)
}