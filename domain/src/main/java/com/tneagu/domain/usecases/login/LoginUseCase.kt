package com.tneagu.domain.usecases.login

interface LoginUseCase {

    suspend fun login(email: String, password: String): Boolean
}