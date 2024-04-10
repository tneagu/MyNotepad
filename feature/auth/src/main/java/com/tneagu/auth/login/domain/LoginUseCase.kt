package com.tneagu.auth.login.domain

interface LoginUseCase {

    suspend fun login(email: String, password: String): Boolean
}