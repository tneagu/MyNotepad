package com.tneagu.data.repository.auth

interface AuthRepo {

    suspend fun login(email: String, password: String): String
}