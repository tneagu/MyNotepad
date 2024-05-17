package com.tneagu.domain.repositories

interface AuthRepo {

    suspend fun login(email: String, password: String): String
}