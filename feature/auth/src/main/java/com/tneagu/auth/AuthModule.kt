package com.tneagu.auth

import com.tneagu.auth.login.domain.repositories.AuthRepo
import com.tneagu.auth.login.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    fun provideLoginUseCase(authRepo: AuthRepo): LoginUseCase = LoginUseCase(authRepo)
}