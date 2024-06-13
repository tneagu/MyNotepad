package com.tneagu.auth.login.domain.repositories

interface AuthRepo {

    suspend fun login(email: String, password: String): String
}